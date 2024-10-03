package com.example.side.techStack.entity;

import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "tech_stack")
@NoArgsConstructor
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 24, unique = true, name = "name")
    private String techName;

    public TechStack(String techName) {
        this.techName = techName;
    }
    @OneToMany(mappedBy = "techStack")
    private List<TechMapping> techMappings;


}
