package com.taskmanager.dto.task;

import com.taskmanager.model.Priority;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Priority priority;
    private LocalDate creationDate;
    private LocalDate dueDate;

    private Long projectId;
    private String projectName;

    private Long assignedUserId;
    private String assignedUserName;
    private String assignedUserEmail;
}
