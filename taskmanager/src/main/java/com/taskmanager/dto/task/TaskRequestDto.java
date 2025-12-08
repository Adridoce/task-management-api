package com.taskmanager.dto.task;


import com.taskmanager.model.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDto {
    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;
    private Priority priority;

    @FutureOrPresent(message = "La fecha de vencimiento no puede ser anterior al dia actual")
    private LocalDate dueDate;

    @NotNull(message = "El proyecto es obligatorio")
    private Long projectId;

    private Long assignedUserId;
}
