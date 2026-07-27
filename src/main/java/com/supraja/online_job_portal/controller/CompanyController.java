package com.supraja.online_job_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.supraja.online_job_portal.dto.CompanyDto;
import com.supraja.online_job_portal.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    // Create Company
    @PostMapping
    public CompanyDto createCompany(
            @Valid @RequestBody CompanyDto companyDto) {

        return companyService.saveCompany(companyDto);
    }


    // Get All Companies
    @GetMapping
    public List<CompanyDto> getAllCompanies() {

        return companyService.getAllCompanies();
    }


    // Get Company By Id
    @GetMapping("/{id}")
    public CompanyDto getCompanyById(
            @PathVariable("id") Long id) {

        return companyService.getCompanyById(id);
    }


    // Update Company
    @PutMapping("/{id}")
    public CompanyDto updateCompany(
            @PathVariable("id") Long id,
            @Valid @RequestBody CompanyDto companyDto) {

        return companyService.updateCompany(id, companyDto);
    }


    // Delete Company
    @DeleteMapping("/{id}")
    public String deleteCompany(
            @PathVariable("id") Long id) {

        companyService.deleteCompany(id);

        return "Company deleted successfully.";
    }
}