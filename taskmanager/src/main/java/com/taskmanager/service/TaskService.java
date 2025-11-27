package com.taskmanager.service;

import com.taskmanager.dto.task.TaskRequestDto;
import com.taskmanager.dto.task.TaskResponseDto;
import com.taskmanager.dto.task.TaskUpdateDto;
import com.taskmanager.entity.Project;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.User;
import com.taskmanager.exceptions.BadRequestException;
import com.taskmanager.exceptions.ResourceNotFoundException;
import com.taskmanager.mapper.TaskMapper;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Status;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskMapper taskMapper;

    public TaskResponseDto createTask(TaskRequestDto dto) {

        // Comprobar si el proyecto existe y cargarlo
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));

        // Si la tarea se crea con usuario lo cargamos
        User user = null;
        if (dto.getAssignedUserId() != null) {
            user = userRepository.findById(dto.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        }

        Task task = taskMapper.toEntity(dto);
        task.setProject(project);
        task.setAssignedUser(user);

        // Si la tarea se crea sin prioridad le asignamos por defecto MEDIUM
        if (task.getPriority() == null) task.setPriority(Priority.MEDIUM);

        // Valores por defecto
        task.setStatus(Status.TO_DO);
        task.setCreationDate(LocalDate.now());

        // Si la tarea se crea con fecha de vencimiento la validamos
        if (task.getDueDate() != null && task.getDueDate().isBefore(task.getCreationDate())) {
            throw new BadRequestException("La fecha de vencimiento no puede ser anterior a la de creación");
        }

        Task created = taskRepository.save(task);
        return taskMapper.toResponse(created);
    }

    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toResponseList(tasks);
    }

    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada"));
        return taskMapper.toResponse(task);
    }

    public List<TaskResponseDto> getTasksByUser(Long id) {
        List<Task> tasks = taskRepository.findByAssignedUser(id);
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponseDto> getTasksByProject(Long id) {
        List<Task> tasks = taskRepository.findByProjectId(id);
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponseDto> getTasksByStatus(Status status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponseDto> getTasksByPriority(Priority priority) {
        List<Task> tasks = taskRepository.findByPriority(priority);
        return taskMapper.toResponseList(tasks);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada"));
        taskRepository.delete(task);
    }

    public TaskResponseDto patchTask(Long id, TaskUpdateDto dto) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada"));

        if (dto.getAssignedUserId() != null) {
            User user = userRepository.findById(dto.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
            existingTask.setAssignedUser(user);
        }

        if (dto.getDueDate() != null && dto.getDueDate().isBefore(existingTask.getCreationDate())) {
            throw new BadRequestException("La fecha de vencimiento no puede ser anterior a la de creación");
        }

        taskMapper.updateTaskFromDto(dto, existingTask);
        Task updated = taskRepository.save(existingTask);
        return taskMapper.toResponse(updated);
    }
}
