package com.microservices.trainer.repo;

import com.microservices.trainer.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByUserName(String userName);
}
