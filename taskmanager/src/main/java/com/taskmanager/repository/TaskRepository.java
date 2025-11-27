package com.taskmanager.repository;

import com.taskmanager.entity.Task;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUser(Long userId);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);
}
