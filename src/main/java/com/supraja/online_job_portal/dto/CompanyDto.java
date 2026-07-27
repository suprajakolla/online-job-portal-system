package com.supraja.online_job_portal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private Long companyId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company email is required")
    @Email(message = "Enter a valid company email")
    private String companyEmail;

    private String companyWebsite;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;
}