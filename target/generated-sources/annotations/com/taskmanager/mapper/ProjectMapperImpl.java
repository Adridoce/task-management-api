package com.taskmanager.mapper;

import com.taskmanager.dto.project.ProjectRequestDto;
import com.taskmanager.dto.project.ProjectResponseDto;
import com.taskmanager.dto.project.ProjectUpdateDto;
import com.taskmanager.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T19:21:51+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponseDto toResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setTaskIds( mapTaskIds( project.getTasks() ) );
        projectResponseDto.setId( project.getId() );
        projectResponseDto.setName( project.getName() );
        projectResponseDto.setDescription( project.getDescription() );
        projectResponseDto.setCreationDate( project.getCreationDate() );

        return projectResponseDto;
    }

    @Override
    public List<ProjectResponseDto> toResponseList(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectResponseDto> list = new ArrayList<ProjectResponseDto>( projects.size() );
        for ( Project project : projects ) {
            list.add( toResponse( project ) );
        }

        return list;
    }

    @Override
    public Project toEntity(ProjectRequestDto project) {
        if ( project == null ) {
            return null;
        }

        Project project1 = new Project();

        project1.setName( project.getName() );
        project1.setDescription( project.getDescription() );

        return project1;
    }

    @Override
    public void updateProjectFromDto(ProjectUpdateDto dto, Project project) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            project.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            project.setDescription( dto.getDescription() );
        }
    }
}
