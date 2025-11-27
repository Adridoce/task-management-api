package com.taskmanager.mapper;

import com.taskmanager.dto.project.ProjectRequestDto;
import com.taskmanager.dto.project.ProjectResponseDto;
import com.taskmanager.dto.project.ProjectUpdateDto;
import com.taskmanager.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    ProjectResponseDto toResponse(Project project);

    List<ProjectResponseDto> toResponseList(List<Project> projects);

    Project toEntity(ProjectRequestDto project);

    void updateProjectFromDto(ProjectUpdateDto dto, @MappingTarget Project project);
}
