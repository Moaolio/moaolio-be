package com.example.side.model.entity;

import jakarta.persistence.*;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "tech_stack")

public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tech_stack_list_id")
    private TechStackList techStackList;
}
