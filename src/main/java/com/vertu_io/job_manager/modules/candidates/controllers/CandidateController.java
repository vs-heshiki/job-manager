package com.vertu_io.job_manager.modules.candidates.controllers;

import com.vertu_io.job_manager.modules.candidates.CandidateEntity;
import com.vertu_io.job_manager.modules.candidates.services.CreateCandidateService;
import com.vertu_io.job_manager.modules.candidates.services.ProfileCandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var output = this.createCandidateService.perform(candidate);

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> profile(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var output = this.profileCandidateService
                    .perform(UUID.fromString(idCandidate.toString()));

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
