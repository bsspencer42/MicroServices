package com.microservices.trainer;

import com.microservices.trainer.domain.Role;
import com.microservices.trainer.domain.Trainer;
import com.microservices.trainer.service.TrainerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TrainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(TrainerService trainerService) {
        return ags -> {
            // DEFAULT ROLES
            trainerService.saveRole(new Role(null, "BASIC"));
            trainerService.saveRole(new Role(null, "BOULDER"));
            trainerService.saveRole(new Role(null, "CASCADE"));
            trainerService.saveRole(new Role(null, "GYM_LEADER"));
            trainerService.saveRole(new Role(null, "RESEARCHER"));

            // DEFAULT USERS
            trainerService.saveTrainer(new Trainer(null, "AshKetchum", "password", "Ash", "Ketchum", "ashketchum@kanto.silphco", new ArrayList<>()));
            trainerService.saveTrainer(new Trainer(null, "ProfessorOak", "password", "Professor", "Oak", "professorOak@kanto.silphco", new ArrayList<>()));
            trainerService.addRoleToUser("AshKetchum", "BASIC");
            trainerService.addRoleToUser("ProfessorOak", "RESEARCHER");
            trainerService.addRoleToUser("ProfessorOak", "BASIC");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
