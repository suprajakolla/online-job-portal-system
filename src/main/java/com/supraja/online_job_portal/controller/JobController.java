package com.supraja.online_job_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supraja.online_job_portal.dto.JobDto;
import com.supraja.online_job_portal.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    // Create Job
    @PostMapping
    public JobDto createJob(
            @Valid @RequestBody JobDto jobDto) {

        return jobService.createJob(jobDto);
    }


    // Get All Jobs
    @GetMapping
    public List<JobDto> getAllJobs() {

        return jobService.getAllJobs();
    }


    // Get Job By Id
    @GetMapping("/{id}")
    public JobDto getJobById(
            @PathVariable("id") Long id) {

        return jobService.getJobById(id);
    }


    // Update Job
    @PutMapping("/{id}")
    public JobDto updateJob(
            @PathVariable("id") Long id,
            @Valid @RequestBody JobDto jobDto) {

        return jobService.updateJob(id, jobDto);
    }


    // Delete Job
    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable("id") Long id) {

        jobService.deleteJob(id);

        return "Job deleted successfully.";
    }
}