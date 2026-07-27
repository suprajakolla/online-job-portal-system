package com.supraja.online_job_portal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supraja.online_job_portal.dto.ApplicationDto;
import com.supraja.online_job_portal.entity.Application;
import com.supraja.online_job_portal.entity.Job;
import com.supraja.online_job_portal.entity.User;
import com.supraja.online_job_portal.exception.ResourceNotFoundException;
import com.supraja.online_job_portal.repository.ApplicationRepository;
import com.supraja.online_job_portal.repository.JobRepository;
import com.supraja.online_job_portal.repository.UserRepository;
import com.supraja.online_job_portal.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public ApplicationDto applyJob(ApplicationDto applicationDto) {

        User user = userRepository.findById(applicationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(applicationDto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = Application.builder()
                .applicationDate(applicationDto.getApplicationDate())
                .status(applicationDto.getStatus())
                .resumePath(applicationDto.getResumePath())
                .user(user)
                .job(job)
                .build();

        Application savedApplication = applicationRepository.save(application);

        return mapToDto(savedApplication);
    }

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
        		.orElseThrow(() ->
                new ResourceNotFoundException("Application not found with id: " + applicationId)
        );

        return mapToDto(application);
    }

    @Override
    public List<ApplicationDto> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto updateApplication(Long applicationId,
                                            ApplicationDto applicationDto) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        User user = userRepository.findById(applicationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(applicationDto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        application.setApplicationDate(applicationDto.getApplicationDate());
        application.setStatus(applicationDto.getStatus());
        application.setResumePath(applicationDto.getResumePath());
        application.setUser(user);
        application.setJob(job);

        Application updatedApplication = applicationRepository.save(application);

        return mapToDto(updatedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {

        applicationRepository.deleteById(applicationId);
    }

    private ApplicationDto mapToDto(Application application) {

        return ApplicationDto.builder()
                .applicationId(application.getApplicationId())
                .applicationDate(application.getApplicationDate())
                .status(application.getStatus())
                .resumePath(application.getResumePath())
                .userId(application.getUser().getUserId())
                .jobId(application.getJob().getJobId())
                .build();
    }
}