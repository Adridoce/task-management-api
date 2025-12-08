package com.taskmanager.mapper;

import com.taskmanager.dto.task.TaskRequestDto;
import com.taskmanager.dto.task.TaskResponseDto;
import com.taskmanager.dto.task.TaskUpdateDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T15:29:35+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskResponseDto toResponse(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        taskResponseDto.setProjectId( taskProjectId( task ) );
        taskResponseDto.setProjectName( taskProjectName( task ) );
        taskResponseDto.setAssignedUserId( taskAssignedUserId( task ) );
        taskResponseDto.setAssignedUserName( taskAssignedUserName( task ) );
        taskResponseDto.setAssignedUserEmail( taskAssignedUserEmail( task ) );
        taskResponseDto.setStatus( mapStatus( task.getStatus() ) );
        taskResponseDto.setId( task.getId() );
        taskResponseDto.setTitle( task.getTitle() );
        taskResponseDto.setDescription( task.getDescription() );
        taskResponseDto.setPriority( task.getPriority() );
        taskResponseDto.setCreationDate( task.getCreationDate() );
        taskResponseDto.setDueDate( task.getDueDate() );

        return taskResponseDto;
    }

    @Override
    public List<TaskResponseDto> toResponseList(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskResponseDto> list = new ArrayList<TaskResponseDto>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toResponse( task ) );
        }

        return list;
    }

    @Override
    public Task toEntity(TaskRequestDto task) {
        if ( task == null ) {
            return null;
        }

        Task task1 = new Task();

        task1.setTitle( task.getTitle() );
        task1.setDescription( task.getDescription() );
        task1.setPriority( task.getPriority() );
        task1.setDueDate( task.getDueDate() );

        return task1;
    }

    @Override
    public void updateTaskFromDto(TaskUpdateDto dto, Task task) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getTitle() != null ) {
            task.setTitle( dto.getTitle() );
        }
        if ( dto.getDescription() != null ) {
            task.setDescription( dto.getDescription() );
        }
        if ( dto.getStatus() != null ) {
            task.setStatus( dto.getStatus() );
        }
        if ( dto.getPriority() != null ) {
            task.setPriority( dto.getPriority() );
        }
        if ( dto.getDueDate() != null ) {
            task.setDueDate( dto.getDueDate() );
        }
    }

    private Long taskProjectId(Task task) {
        if ( task == null ) {
            return null;
        }
        Project project = task.getProject();
        if ( project == null ) {
            return null;
        }
        Long id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String taskProjectName(Task task) {
        if ( task == null ) {
            return null;
        }
        Project project = task.getProject();
        if ( project == null ) {
            return null;
        }
        String name = project.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long taskAssignedUserId(Task task) {
        if ( task == null ) {
            return null;
        }
        User assignedUser = task.getAssignedUser();
        if ( assignedUser == null ) {
            return null;
        }
        Long id = assignedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String taskAssignedUserName(Task task) {
        if ( task == null ) {
            return null;
        }
        User assignedUser = task.getAssignedUser();
        if ( assignedUser == null ) {
            return null;
        }
        String name = assignedUser.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String taskAssignedUserEmail(Task task) {
        if ( task == null ) {
            return null;
        }
        User assignedUser = task.getAssignedUser();
        if ( assignedUser == null ) {
            return null;
        }
        String email = assignedUser.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
