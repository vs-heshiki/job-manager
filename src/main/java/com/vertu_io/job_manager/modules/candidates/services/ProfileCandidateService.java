package com.vertu_io.job_manager.modules.candidates.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.modules.candidates.CandidateRepository;
import com.vertu_io.job_manager.modules.candidates.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO perform(UUID id) {
        var candidate = candidateRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .curriculum(candidate.getCurriculum())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .build();
    }
}
