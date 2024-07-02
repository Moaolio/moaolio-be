package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@lombok.Getter
@lombok.Setter
@jakarta.persistence.Table(name = "post_like")
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private Post post;
}
