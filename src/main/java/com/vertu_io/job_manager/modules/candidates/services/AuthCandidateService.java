package com.vertu_io.job_manager.modules.candidates.services;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.modules.candidates.CandidateRepository;
import com.vertu_io.job_manager.modules.candidates.dto.AuthCandidateRequestDTO;
import com.vertu_io.job_manager.modules.candidates.dto.AuthCandidateResponseDTO;
import com.vertu_io.job_manager.providers.JWTProvider;

@Service
public class AuthCandidateService {

    @Value("${security.candidate.secret.key}")
    private String jwtSecret;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public AuthCandidateResponseDTO perform(AuthCandidateRequestDTO authCandidateRequestDTO)
            throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/Password incorrect");
                });

        var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var token = jwtProvider.createCandidateToken(candidate.getId().toString(), jwtSecret);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expiresIn(Instant.now().plus(Duration.ofHours(2)))
                .build();
    }
}
