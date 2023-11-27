package com.vertu_io.job_manager.modules.companies.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vertu_io.job_manager.modules.companies.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
