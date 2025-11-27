package com.taskmanager.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name;

    @Email(message = "El email no es válido")
    private String email;

    private String password;
}
