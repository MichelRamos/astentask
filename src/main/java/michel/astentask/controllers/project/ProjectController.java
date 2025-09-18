package michel.astentask.controllers.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import michel.astentask.dtos.request.ProjectRequest;
import michel.astentask.dtos.response.ProjectResponse;
import michel.astentask.entities.User;
import michel.astentask.services.ProjectService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest body) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String name = user.getName();

        ResponseEntity<ProjectResponse> projectResponse = projectService.createProject(body, name);

        return projectResponse;
    }
    

    @GetMapping()
    public Page<ProjectResponse> getUserProjects(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            String name) {

                Page<ProjectResponse> projects = projectService.findUserProjects(pageable, name);

        return projects;
    }
    
    
}
