package com.supraja.online_job_portal.service;

import java.util.List;

import com.supraja.online_job_portal.dto.JobDto;

public interface JobService {

    JobDto createJob(JobDto jobDto);

    JobDto getJobById(Long jobId);

    List<JobDto> getAllJobs();

    JobDto updateJob(Long jobId, JobDto jobDto);

    void deleteJob(Long jobId);
}