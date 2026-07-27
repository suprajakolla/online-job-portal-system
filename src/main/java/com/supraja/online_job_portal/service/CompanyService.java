package com.supraja.online_job_portal.service;

import java.util.List;

import com.supraja.online_job_portal.dto.CompanyDto;

public interface CompanyService {

    CompanyDto saveCompany(CompanyDto companyDto);

    CompanyDto getCompanyById(Long companyId);

    List<CompanyDto> getAllCompanies();

    CompanyDto updateCompany(Long companyId, CompanyDto companyDto);

    void deleteCompany(Long companyId);

}