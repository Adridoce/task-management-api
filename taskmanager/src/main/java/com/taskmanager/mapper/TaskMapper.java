package com.taskmanager.mapper;

import com.taskmanager.dto.task.TaskRequestDto;
import com.taskmanager.dto.task.TaskResponseDto;
import com.taskmanager.dto.task.TaskUpdateDto;
import com.taskmanager.entity.Task;
import com.taskmanager.model.Status;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.name", target = "projectName")
    @Mapping(source = "assignedUser.id", target = "assignedUserId")
    @Mapping(source = "assignedUser.name", target = "assignedUserName")
    @Mapping(source = "assignedUser.email", target = "assignedUserEmail")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatus")
    TaskResponseDto toResponse(Task task);

    List<TaskResponseDto> toResponseList(List<Task> tasks);

    Task toEntity(TaskRequestDto task);

    void updateTaskFromDto(TaskUpdateDto dto, @MappingTarget Task task);

    @Named("mapStatus")
    default String mapStatus(Status status) {
        if (status == null) return null;

        return switch (status) {
            case TO_DO -> "pendiente";
            case IN_PROGRESS -> "En progreso";
            case DONE -> "Completada";
        };
    }
}
