package com.vertu_io.job_manager.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vertu_io.job_manager.modules.companies.dto.AuthCompanyDTO;
import com.vertu_io.job_manager.modules.companies.services.AuthCompanyService;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyService authCompanyService;

    @PostMapping("/company")
    public ResponseEntity<Object> authenticate(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var output = this.authCompanyService.perform(authCompanyDTO);

            return ResponseEntity.ok().body(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
