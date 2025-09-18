package michel.astentask.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import michel.astentask.dtos.request.ProjectRequest;
import michel.astentask.dtos.response.ProjectResponse;

public interface ProjectService {

    ResponseEntity<ProjectResponse> createProject(ProjectRequest body, String name);

    Page<ProjectResponse> findUserProjects(Pageable pageable, String name);
    
}
