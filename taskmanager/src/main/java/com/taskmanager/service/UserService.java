package com.taskmanager.service;

import com.taskmanager.dto.user.UserRequestDto;
import com.taskmanager.dto.user.UserResponseDto;
import com.taskmanager.dto.user.UserUpdateDto;
import com.taskmanager.entity.User;
import com.taskmanager.exceptions.BadRequestException;
import com.taskmanager.exceptions.ConflictException;
import com.taskmanager.exceptions.ResourceNotFoundException;
import com.taskmanager.mapper.UserMapper;
import com.taskmanager.model.Role;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDto createUser(UserRequestDto userRequest) {

        // Email unico
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new ConflictException("El email ya esta registrado");
        }

        User user = userMapper.toEntity(userRequest);
        user.setRole(Role.USER);

        User created = userRepository.save(user);
        return userMapper.toResponse(created);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return userMapper.toResponse(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return userMapper.toResponse(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (!user.getTasks().isEmpty()) {
            throw new BadRequestException("No se puede eliminar un usuario con tareas asignadas");
        }
        userRepository.delete(user);
    }

    public UserResponseDto patchUser(Long id, UserUpdateDto dto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (dto.getEmail() != null) {
            userRepository.findByEmail(dto.getEmail())
                    .filter(u -> !u.getId().equals(id))
                    .ifPresent(u -> {
                        throw new ConflictException("El email ya esta registrado");
                    });
        }

        if (dto.getName() != null && dto.getName().isBlank()) {
            throw new BadRequestException("El nombre no puede estar vacío");
        }

        if (dto.getPassword() != null && dto.getPassword().length() < 6) {
            throw new BadRequestException("La contraseña debe tener como mínimo 6 carácteres");
        }

        userMapper.updateUserFromDto(dto, existingUser);

        User updated = userRepository.save(existingUser);

        return userMapper.toResponse(updated);
    }
}
