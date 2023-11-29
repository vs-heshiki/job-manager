package com.vertu_io.job_manager.modules.companies.services;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.modules.candidates.dto.AuthCandidateResponseDTO;
import com.vertu_io.job_manager.modules.companies.dto.AuthCompanyRequestDTO;
import com.vertu_io.job_manager.modules.companies.repositories.CompanyRepository;
import com.vertu_io.job_manager.providers.JWTProvider;

@Service
public class AuthCompanyService {

    @Value("${security.company.secret.key}")
    private String jwtSecret;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public AuthCandidateResponseDTO perform(AuthCompanyRequestDTO authCompanyRequestDTO)
            throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyRequestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company/Password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyRequestDTO.password(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var token = jwtProvider.createCompanyToken(company.getId().toString(), jwtSecret);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expiresIn(Instant.now().plus(Duration.ofHours(2)))
                .build();
    }
}
