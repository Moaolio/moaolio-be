package com.example.side.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "job_list")
public class JobList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "jobList" , fetch = FetchType.LAZY)
    private List<Job> jobs;
}
