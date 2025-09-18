package michel.astentask.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import michel.astentask.dtos.request.ProjectRequest;
import michel.astentask.dtos.response.ProjectResponse;
import michel.astentask.entities.Project;
import michel.astentask.entities.User;
import michel.astentask.enums.ProjectStatus;
import michel.astentask.mapper.ProjectMapper;
import michel.astentask.repositories.ProjectsRepository;
import michel.astentask.repositories.UsersRepository;
import michel.astentask.services.ProjectService;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ProjectsRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public ResponseEntity<ProjectResponse> createProject(ProjectRequest body, String name) {
        
        User owner = userRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Project project = new Project();
        project.setName(body.getName());
        project.setDescription(body.getDescription());
        project.setStatus(ProjectStatus.PLANNING);
        project.setOwner(owner);
        Project savedProject = projectRepository.save(project);

        return new ResponseEntity<>(new ProjectResponse(savedProject), HttpStatus.CREATED);
    }

    @Override
    public Page<ProjectResponse> findUserProjects(Pageable pageable, String name) {
        
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Page<Project> projects = projectRepository.findByOwnerOrMember(user.getId(), pageable);

        return projects.map(projectMapper::toProjectResponse);

    }
}
