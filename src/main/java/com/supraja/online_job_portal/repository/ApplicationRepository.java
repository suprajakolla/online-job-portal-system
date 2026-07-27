package com.supraja.online_job_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supraja.online_job_portal.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserUserId(Long userId);

    List<Application> findByJobJobId(Long jobId);

}