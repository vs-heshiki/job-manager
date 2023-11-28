package com.vertu_io.job_manager.modules.companies.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String title;
    private String description;
    private String level;
    private String benefits;
}
