package com.supraja.online_job_portal.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "APPLICATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(
            name = "application_seq",
            sequenceName = "APPLICATION_SEQ",
            allocationSize = 1
    )
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "APPLICATION_DATE")
    private LocalDate applicationDate;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "RESUME_PATH", length = 255)
    private String resumePath;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "JOB_ID", nullable = false)
    private Job job;
}