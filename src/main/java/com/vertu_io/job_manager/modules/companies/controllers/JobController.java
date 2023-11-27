package com.vertu_io.job_manager.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vertu_io.job_manager.modules.companies.entities.JobEntity;
import com.vertu_io.job_manager.modules.companies.services.CreateJobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity) {
        try {
            var output = createJobService.perform(jobEntity);

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
