package com.microservices.trainer.api;

import com.microservices.trainer.domain.Role;
import com.microservices.trainer.domain.Trainer;
import com.microservices.trainer.service.TrainerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping("/trainer/save")
    public ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer) {
        // TODO Add code for bad input
        // TODO TRY.CATCH for trainer already exists
        log.info("new customer registration {}", trainer);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/trainer/save").toUriString());
        // Register trainer
        return ResponseEntity.created(uri).body(trainerService.saveTrainer(trainer));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(trainerService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        trainerService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<Trainer>> getTrainers() {
        return ResponseEntity.ok().body(trainerService.getTrainers());
    }

}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}
