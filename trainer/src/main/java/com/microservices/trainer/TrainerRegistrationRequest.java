package com.microservices.trainer;

public record TrainerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
