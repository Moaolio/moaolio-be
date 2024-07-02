package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "community")
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String occupation;
    @ManyToOne
    private User user;
}
