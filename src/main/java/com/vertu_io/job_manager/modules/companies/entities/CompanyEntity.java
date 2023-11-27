package com.vertu_io.job_manager.modules.companies.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    @Length(min = 3, message = ("Name must be at least 3 characters"))
    private String name;

    @Length(min = 5, message = ("Username must be at least 5 characters"))
    @Pattern(regexp = "\\S+")
    private String username;

    @Email
    private String email;

    @Length(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @URL(message = "Must be a valid URL")
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
