package com.microservices.trainer.api;

import com.microservices.trainer.TrainerResponse;
import com.microservices.trainer.domain.Trainer;
import com.microservices.trainer.service.TrainerService;
import com.microservices.trainer.service.TrainerServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerResponse> registerTrainer(@RequestBody Trainer trainerRequest) {
        // TODO Add code for bad input
        // TODO TRY.CATCH for trainer already exists
        log.info("new customer registration {}", trainerRequest);
        // Register trainer
        Trainer trainer = trainerService.saveTrainer(trainerRequest);
        // Create Response
        TrainerResponse response = createTrainerRegistrationResponse(trainer);
        return ResponseEntity.ok(response);
    }

    private TrainerResponse createTrainerRegistrationResponse(Trainer trainer) {
        TrainerResponse trainerResponse = TrainerResponse.builder()
                .trainerId(trainer.getId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .email(trainer.getEmail())
                .build();
        return trainerResponse;
    }


}
