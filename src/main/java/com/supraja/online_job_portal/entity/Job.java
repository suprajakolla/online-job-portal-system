package com.supraja.online_job_portal.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "JOBS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    @SequenceGenerator(
            name = "job_seq",
            sequenceName = "JOB_SEQ",
            allocationSize = 1
    )
    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "JOB_TITLE", nullable = false, length = 100)
    private String jobTitle;

    @Column(name = "JOB_DESCRIPTION", nullable = false, length = 2000)
    private String jobDescription;

    @Column(name = "LOCATION", nullable = false, length = 100)
    private String location;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "EXPERIENCE", length = 50)
    private String experience;

    @Column(name = "JOB_TYPE", length = 50)
    private String jobType;

    @Column(name = "POSTED_DATE")
    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;
}