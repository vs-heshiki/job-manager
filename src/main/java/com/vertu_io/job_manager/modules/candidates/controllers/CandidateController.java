package com.vertu_io.job_manager.modules.candidates.controllers;

import com.vertu_io.job_manager.modules.candidates.CandidateEntity;
import com.vertu_io.job_manager.modules.candidates.services.CreateCandidateServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateServices createCandidateServices;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var output = createCandidateServices.perform(candidate);

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
