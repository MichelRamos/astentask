package michel.astentask.dtos.request;

import lombok.Data;
import michel.astentask.entities.Project;

@Data
public class ProjectRequest {
    
    private String name;
    private String description;

    public ProjectRequest() {}

    public ProjectRequest(Project project){
        super();
        this.name = project.getName();
        this.description = project.getDescription();
    }

}
