package com.microservices.trainer.service;

import com.microservices.trainer.domain.Role;
import com.microservices.trainer.domain.Trainer;

import java.util.List;

public interface TrainerServiceInterface {
    Trainer saveTrainer(Trainer trainer);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    Trainer getTrainer(String userName);
    List<Trainer> getTrainers();
}
