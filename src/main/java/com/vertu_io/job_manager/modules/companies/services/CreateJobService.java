package com.vertu_io.job_manager.modules.companies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vertu_io.job_manager.modules.companies.entities.JobEntity;
import com.vertu_io.job_manager.modules.companies.repositories.JobRepository;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity perform(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);
    }
}
