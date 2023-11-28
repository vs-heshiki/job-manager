package com.vertu_io.job_manager.modules.companies.services;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.modules.companies.dto.AuthCompanyDTO;
import com.vertu_io.job_manager.modules.companies.repositories.CompanyRepository;
import com.vertu_io.job_manager.providers.JWTProvider;

@Service
public class AuthCompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public String perform(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company/Password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        return jwtProvider.createToken(company.getId().toString());
    }
}
