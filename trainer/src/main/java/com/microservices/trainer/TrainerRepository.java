package com.microservices.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
