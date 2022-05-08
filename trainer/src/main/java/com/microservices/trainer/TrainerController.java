package com.microservices.trainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/trainer")
public record TrainerController(TrainerService trainerService) {
    public void registerTrainer(@RequestBody TrainerRegistrationRequest trainerRegistrationRequest) {
        log.info("new customer registration {}", trainerRegistrationRequest);
        trainerService.registerTrainer(trainerRegistrationRequest);
    }

}
