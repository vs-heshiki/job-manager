package com.vertu_io.job_manager.modules.companies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.exceptions.UserAlreadyExistsException;
import com.vertu_io.job_manager.modules.companies.entities.CompanyEntity;
import com.vertu_io.job_manager.modules.companies.repositories.CompanyRepository;

@Service
public class CreateCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity perform(CompanyEntity companyEntity) {
        companyRepository
                .findByEmailOrUsername(companyEntity.getEmail(), companyEntity.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException();
                });

        return this.companyRepository.save(companyEntity);
    }
}
