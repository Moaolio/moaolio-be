package com.example.side.model.entity;

import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
@jakarta.persistence.Table(name = "post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private UserPost userPost;
}
