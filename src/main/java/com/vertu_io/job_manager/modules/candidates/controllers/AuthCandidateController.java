package com.vertu_io.job_manager.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vertu_io.job_manager.modules.candidates.dto.AuthCandidateRequestDTO;
import com.vertu_io.job_manager.modules.candidates.services.AuthCandidateService;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("/candidate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var output = this.authCandidateService.perform(authCandidateRequestDTO);

            return ResponseEntity.ok().body(output);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
