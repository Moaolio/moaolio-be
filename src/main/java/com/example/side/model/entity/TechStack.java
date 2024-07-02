package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "tech_stack")
@NoArgsConstructor
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String techName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
