package com.microservices.trainer.service;

import com.microservices.trainer.domain.Role;
import com.microservices.trainer.domain.Trainer;
import com.microservices.trainer.repo.RoleRepository;
import com.microservices.trainer.repo.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainerService implements TrainerServiceInterface {
    private final TrainerRepository trainerRepo;
    private final RoleRepository roleRepo;

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        log.info("Saving new trainer {} to database", trainer.getUserName());
        return trainerRepo.save(trainer);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to trainer {} ", roleName, userName);
        Trainer trainer = trainerRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        trainer.getRoles().add(role);
    }

    @Override
    public Trainer getTrainer(String userName) {
        log.info("Fetching user {}", userName);
        return trainerRepo.findByUserName(userName);
    }

    @Override
    public List<Trainer> getTrainers() {
        log.info("Fetching all trainers");
        return trainerRepo.findAll();
    }
}
