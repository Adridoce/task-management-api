package com.taskmanager.mapper;

import com.taskmanager.dto.project.ProjectRequestDto;
import com.taskmanager.dto.project.ProjectResponseDto;
import com.taskmanager.dto.project.ProjectUpdateDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Task;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    @Mapping(source = "tasks", target = "taskIds", qualifiedByName = "mapTaskIds")
    ProjectResponseDto toResponse(Project project);

    List<ProjectResponseDto> toResponseList(List<Project> projects);

    Project toEntity(ProjectRequestDto project);

    void updateProjectFromDto(ProjectUpdateDto dto, @MappingTarget Project project);

    @Named("mapTaskIds")
    default List<Long> mapTaskIds(List<Task> tasks) {
        if (tasks == null) return new ArrayList<>();

        return tasks.stream()
                .map(Task::getId)
                .collect(Collectors.toList());
    }
}
