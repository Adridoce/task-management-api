package com.taskmanager.dto.register;

import com.taskmanager.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
