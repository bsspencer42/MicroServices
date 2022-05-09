package com.microservices.trainer.service;

import com.microservices.trainer.domain.Role;
import com.microservices.trainer.domain.Trainer;
import com.microservices.trainer.repo.RoleRepository;
import com.microservices.trainer.repo.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainerService implements TrainerServiceInterface, UserDetailsService {
    private final TrainerRepository trainerRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Trainer  trainer = trainerRepo.findByUserName(userName);
        if (trainer == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User {} found in database", userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        trainer.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(trainer.getUserName(), trainer.getPassword(), authorities);
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        log.info("Saving new trainer {} to database", trainer.getUserName());
        trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
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
