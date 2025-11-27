package com.taskmanager.entity;

import com.taskmanager.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter                 // Genera getters
@Setter                 // Genera setters
@NoArgsConstructor      // Constructor sin argumentos
@AllArgsConstructor     // Constructor con todos los campos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "assignedUser")
    private List<Task> tasks = new ArrayList<>();
}
