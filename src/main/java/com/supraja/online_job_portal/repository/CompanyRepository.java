package com.supraja.online_job_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supraja.online_job_portal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyEmail(String companyEmail);

    boolean existsByCompanyEmail(String companyEmail);
}