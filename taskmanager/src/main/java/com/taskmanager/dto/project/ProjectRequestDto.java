package com.taskmanager.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequestDto {
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;
    private String description;
}
