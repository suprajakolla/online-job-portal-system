package com.supraja.online_job_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supraja.online_job_portal.dto.ApplicationDto;
import com.supraja.online_job_portal.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ApplicationDto applyJob(
            @Valid @RequestBody ApplicationDto applicationDto) {

        return applicationService.applyJob(applicationDto);
    }

    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationDto getApplicationById(@PathVariable("id") Long id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public ApplicationDto updateApplication(@PathVariable("id") Long id,
                                            @Valid @RequestBody ApplicationDto applicationDto) {
        return applicationService.updateApplication(id, applicationDto);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable("id") Long id) {
        applicationService.deleteApplication(id);
        return "Application deleted successfully.";
    }
}