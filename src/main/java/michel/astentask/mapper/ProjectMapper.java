package michel.astentask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import michel.astentask.dtos.response.ProjectResponse;
import michel.astentask.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectResponse toProjectResponse(Project project);
    
}
