package com.example.side.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@jakarta.persistence.Table(name = "user_post_comments")
@lombok.Getter
@lombok.Setter

public class UserPostComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private UserPost userPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
