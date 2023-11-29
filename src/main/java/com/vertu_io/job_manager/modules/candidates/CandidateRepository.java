package com.vertu_io.job_manager.modules.candidates;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByEmailOrUsername(String email, String userName);

    Optional<CandidateEntity> findByUsername(String userName);
}
