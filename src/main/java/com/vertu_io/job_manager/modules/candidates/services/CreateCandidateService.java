package com.vertu_io.job_manager.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.exceptions.UserAlreadyExistsException;
import com.vertu_io.job_manager.modules.candidates.CandidateEntity;
import com.vertu_io.job_manager.modules.candidates.CandidateRepository;

@Service
public class CreateCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity perform(CandidateEntity candidate) {
        candidateRepository
                .findByEmailOrUsername(candidate.getEmail(), candidate.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });

        String password = this.passwordEncoder.encode(candidate.getPassword());

        candidate.setPassword(password);

        return this.candidateRepository.save(candidate);
    }
}
