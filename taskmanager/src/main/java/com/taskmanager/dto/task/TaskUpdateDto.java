package com.taskmanager.dto.task;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskUpdateDto {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private Long projectId;
    private Long assignedUserId;
}
