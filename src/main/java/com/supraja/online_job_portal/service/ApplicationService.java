package com.supraja.online_job_portal.service;

import java.util.List;

import com.supraja.online_job_portal.dto.ApplicationDto;

public interface ApplicationService {

    ApplicationDto applyJob(ApplicationDto applicationDto);

    ApplicationDto getApplicationById(Long applicationId);

    List<ApplicationDto> getAllApplications();

    ApplicationDto updateApplication(Long applicationId, ApplicationDto applicationDto);

    void deleteApplication(Long applicationId);
}