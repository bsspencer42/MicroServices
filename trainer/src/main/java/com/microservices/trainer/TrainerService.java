package com.microservices.trainer;

import org.springframework.stereotype.Service;

@Service
public record TrainerService(TrainerRepository trainerRepository) {
    public void registerTrainer(TrainerRegistrationRequest trainerRegistrationRequest) {
        Trainer trainer = Trainer.builder()
                .firstName(trainerRegistrationRequest.firstName())
                .lastName(trainerRegistrationRequest.lastName())
                .email(trainerRegistrationRequest.email())
                .build();
        trainerRepository.save(trainer);
    }
}
