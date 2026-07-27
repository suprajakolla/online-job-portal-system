package com.supraja.online_job_portal.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {

    private Long applicationId;

    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;

    @NotBlank(message = "Application status is required")
    private String status;

    @NotBlank(message = "Resume path is required")
    private String resumePath;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Job ID is required")
    private Long jobId;
}