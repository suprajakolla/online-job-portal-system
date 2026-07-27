package com.supraja.online_job_portal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supraja.online_job_portal.dto.JobDto;
import com.supraja.online_job_portal.entity.Company;
import com.supraja.online_job_portal.entity.Job;
import com.supraja.online_job_portal.exception.ResourceNotFoundException;
import com.supraja.online_job_portal.repository.CompanyRepository;
import com.supraja.online_job_portal.repository.JobRepository;
import com.supraja.online_job_portal.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public JobDto createJob(JobDto jobDto) {

        Company company = companyRepository.findById(jobDto.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + jobDto.getCompanyId()
                        )
                );

        Job job = Job.builder()
                .jobTitle(jobDto.getJobTitle())
                .jobDescription(jobDto.getJobDescription())
                .location(jobDto.getLocation())
                .salary(jobDto.getSalary())
                .experience(jobDto.getExperience())
                .jobType(jobDto.getJobType())
                .postedDate(jobDto.getPostedDate())
                .company(company)
                .build();

        Job savedJob = jobRepository.save(job);

        return mapToDto(savedJob);
    }


    @Override
    public JobDto getJobById(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        return mapToDto(job);
    }


    @Override
    public List<JobDto> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public JobDto updateJob(Long jobId, JobDto jobDto) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );


        Company company = companyRepository.findById(jobDto.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + jobDto.getCompanyId()
                        )
                );


        job.setJobTitle(jobDto.getJobTitle());
        job.setJobDescription(jobDto.getJobDescription());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());
        job.setExperience(jobDto.getExperience());
        job.setJobType(jobDto.getJobType());
        job.setPostedDate(jobDto.getPostedDate());
        job.setCompany(company);

        Job updatedJob = jobRepository.save(job);

        return mapToDto(updatedJob);
    }


    @Override
    public void deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        jobRepository.delete(job);
    }


    private JobDto mapToDto(Job job) {

        return JobDto.builder()
                .jobId(job.getJobId())
                .jobTitle(job.getJobTitle())
                .jobDescription(job.getJobDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .postedDate(job.getPostedDate())
                .companyId(job.getCompany().getCompanyId())
                .build();
    }
}