package com.taskmanager.config;

import com.taskmanager.entity.User;
import com.taskmanager.model.Role;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setName("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword("4dmin123");      // TODO: Encriptar password
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("✔ Super admin creado: "
                    + admin.getEmail() + "/" + admin.getPassword());
        }
    }
}
