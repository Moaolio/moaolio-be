package com.example.side.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "tech_stack_list")

public class TechStackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "techStackList", fetch = FetchType.LAZY)
    private List<TechStack> techStacks;

}
