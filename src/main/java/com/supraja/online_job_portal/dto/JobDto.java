package com.supraja.online_job_portal.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDto {

    private Long jobId;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Job description is required")
    private String jobDescription;

    @NotBlank(message = "Location is required")
    private String location;

    @Positive(message = "Salary must be positive")
    private Double salary;

    private String experience;

    private String jobType;

    private LocalDate postedDate;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}