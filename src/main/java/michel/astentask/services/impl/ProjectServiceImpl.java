package michel.astentask.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import michel.astentask.dtos.request.ProjectRequest;
import michel.astentask.dtos.response.ProjectResponse;
import michel.astentask.entities.Project;
import michel.astentask.entities.Role;
import michel.astentask.entities.User;
import michel.astentask.enums.ProjectStatus;
import michel.astentask.repositories.ProjectsRepository;
import michel.astentask.repositories.RolesRepository;
import michel.astentask.repositories.UsersRepository;
import michel.astentask.services.ProjectService;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private ProjectsRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<ProjectResponse> createProject(ProjectRequest body, String email) {
        
        User owner = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Role ownerRole = roleRepository.findByName("PROJECT_MANAGER")
            .orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(ownerRole);
        owner.setRoles(roles);
        
        Project project = new Project();
        project.setName(body.getName());
        project.setDescription(body.getDescription());
        project.setStatus(ProjectStatus.PLANNING);
        project.setOwner(owner);
        Project savedProject = projectRepository.save(project);

        return new ResponseEntity<>(new ProjectResponse(savedProject), HttpStatus.CREATED);
    }

    @Override
    public Page<ProjectResponse> findUserProjects(Pageable pageable, String email) {
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Page<Project> projects = projectRepository.findByOwnerOrMember(user.getId(), pageable);        
        
        Page<ProjectResponse> projectResponse = projects.map(project -> modelMapper.map(project, ProjectResponse.class));
        
        return projectResponse;
    }
}
