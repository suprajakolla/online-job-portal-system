package com.supraja.online_job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMPANIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(
            name = "company_seq",
            sequenceName = "COMPANY_SEQ",
            allocationSize = 1)
    private Long companyId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    private String companyWebsite;

    @Column(nullable = false)
    private String location;

    @Column(length = 1000)
    private String description;
}