package com.example.side.model.entity;

import jakarta.persistence.*;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "community")

public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String occupation;


}
