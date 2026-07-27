package com.supraja.online_job_portal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supraja.online_job_portal.dto.CompanyDto;
import com.supraja.online_job_portal.entity.Company;
import com.supraja.online_job_portal.exception.ResourceNotFoundException;
import com.supraja.online_job_portal.repository.CompanyRepository;
import com.supraja.online_job_portal.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) {

        Company company = Company.builder()
                .companyName(companyDto.getCompanyName())
                .companyEmail(companyDto.getCompanyEmail())
                .companyWebsite(companyDto.getCompanyWebsite())
                .location(companyDto.getLocation())
                .description(companyDto.getDescription())
                .build();

        Company savedCompany = companyRepository.save(company);

        return convertToDto(savedCompany);
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                new ResourceNotFoundException("Company not found with id: " + companyId)
                		);

        return convertToDto(company);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyWebsite(companyDto.getCompanyWebsite());
        company.setLocation(companyDto.getLocation());
        company.setDescription(companyDto.getDescription());

        Company updatedCompany = companyRepository.save(company);

        return convertToDto(updatedCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    private CompanyDto convertToDto(Company company) {

        return CompanyDto.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .companyEmail(company.getCompanyEmail())
                .companyWebsite(company.getCompanyWebsite())
                .location(company.getLocation())
                .description(company.getDescription())
                .build();
    }
}