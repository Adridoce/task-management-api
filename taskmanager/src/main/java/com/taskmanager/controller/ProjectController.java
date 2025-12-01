package com.taskmanager.controller;

import com.taskmanager.dto.project.ProjectRequestDto;
import com.taskmanager.dto.project.ProjectResponseDto;
import com.taskmanager.dto.project.ProjectUpdateDto;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(projectService.searchProjectsByName(name));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> patchProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDto updatedProject) {
        return ResponseEntity.ok(projectService.patchProject(id, updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
