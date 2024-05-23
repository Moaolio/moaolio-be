package com.example.side.model.entity;

import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
@jakarta.persistence.Table(name = "job")

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobList jobList;


}
