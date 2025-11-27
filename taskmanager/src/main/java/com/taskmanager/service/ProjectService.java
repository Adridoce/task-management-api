package com.taskmanager.service;

import com.taskmanager.dto.project.ProjectRequestDto;
import com.taskmanager.dto.project.ProjectResponseDto;
import com.taskmanager.dto.project.ProjectUpdateDto;
import com.taskmanager.entity.Project;
import com.taskmanager.exceptions.BadRequestException;
import com.taskmanager.exceptions.ConflictException;
import com.taskmanager.exceptions.ResourceNotFoundException;
import com.taskmanager.mapper.ProjectMapper;
import com.taskmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public ProjectResponseDto createProject(ProjectRequestDto dto) {

        // Nombre unico
        if (projectRepository.findByName(dto.getName()).isPresent()) {
            throw new ConflictException("Ya existe un proyecto con ese nombre");
        }

        Project project = projectMapper.toEntity(dto);
        project.setCreationDate(LocalDate.now());
        project.setTasks(new ArrayList<>());

        Project created = projectRepository.save(project);
        return projectMapper.toResponse(created);
    }

    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toResponseList(projects);
    }

    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));
        return projectMapper.toResponse(project);
    }

    public List<ProjectResponseDto> searchProjectsByName(String name) {
        List<Project> projects = projectRepository.findByNameContainingIgnoreCase(name);
        return projectMapper.toResponseList(projects);
    }

    public ProjectResponseDto patchProject(Long id, ProjectUpdateDto dto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (dto.getName() != null) {

            if (dto.getName().isBlank()) {
                throw new BadRequestException("El nombre no puede estar vacío");
            }

            if (!existingProject.getName().equals(dto.getName()) &&
                    projectRepository.findByName(dto.getName()).isPresent()) {
                throw new ConflictException("Ya existe un proyecto con ese nombre");
            }
            existingProject.setName(dto.getName());
        }

        projectMapper.updateProjectFromDto(dto, existingProject);
        Project updated = projectRepository.save(existingProject);

        return projectMapper.toResponse(updated);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        if (!project.getTasks().isEmpty()) {
            throw new BadRequestException("No puedes eliminar un proyecto con tareas asignadas");
        }
        projectRepository.delete(project);
    }
}
