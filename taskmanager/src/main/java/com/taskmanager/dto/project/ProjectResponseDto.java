package com.taskmanager.dto.project;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private List<Long> taskIds = new ArrayList<>();
}
