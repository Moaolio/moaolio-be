package com.example.side.techStack.entity;

import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "tech_stack")
@NoArgsConstructor
public class TechStack {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String techName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
