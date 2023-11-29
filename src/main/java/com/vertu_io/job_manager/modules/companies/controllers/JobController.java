package com.vertu_io.job_manager.modules.companies.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vertu_io.job_manager.modules.companies.dto.CreateJobDTO;
import com.vertu_io.job_manager.modules.companies.entities.JobEntity;
import com.vertu_io.job_manager.modules.companies.services.CreateJobService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class JobController {

    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/job")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO,
            HttpServletRequest httpServletRequest) {
        try {
            var companyId = httpServletRequest.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .title(createJobDTO.getTitle())
                    .description(createJobDTO.getDescription())
                    .benefits(createJobDTO.getBenefits())
                    .level(createJobDTO.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            var output = createJobService.perform(jobEntity);

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
