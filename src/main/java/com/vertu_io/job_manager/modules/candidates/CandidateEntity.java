package com.vertu_io.job_manager.modules.candidates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Length(min = 5, message = "Username must be at least 5 characters")
    @Pattern(regexp = "\\S+", message = "Username cannot contain spaces")
    private String username;

    @Email(message = "Email must be valid")
    private String email;

    @Length(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
