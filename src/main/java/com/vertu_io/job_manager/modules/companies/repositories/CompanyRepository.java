package com.vertu_io.job_manager.modules.companies.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vertu_io.job_manager.modules.companies.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByEmailOrUsername(String email, String username);

    Optional<CompanyEntity> findByUsername(String username);
}
