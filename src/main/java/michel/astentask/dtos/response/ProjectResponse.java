package michel.astentask.dtos.response;

import java.util.Set;

import lombok.Data;
import michel.astentask.entities.Project;
import michel.astentask.entities.User;
import michel.astentask.enums.ProjectStatus;

@Data
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private ProjectStatus projectStatus;
    private User owner;
    private Set<User> members;
    
    public ProjectResponse(){}

    public ProjectResponse(Project project) {
        super();
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.projectStatus = project.getStatus();
        this.owner= project.getOwner();
        this.members= project.getMembers();
    }
}
