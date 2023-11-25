package com.vertu_io.job_manager.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.exceptions.UserAlreadyExistsException;
import com.vertu_io.job_manager.modules.candidates.CandidateEntity;
import com.vertu_io.job_manager.modules.candidates.CandidateRepository;

@Service
public class CreateCandidateServices {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity perform(CandidateEntity candidate) {
        candidateRepository.findByEmailOrUsername(candidate.getEmail(), candidate.getUsername()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        return this.candidateRepository.save(candidate);
    }
}
