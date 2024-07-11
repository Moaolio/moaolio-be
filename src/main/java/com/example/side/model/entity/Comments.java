package com.example.side.model.entity;

import com.example.side.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_post_comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comments(String description, Post post, User user) {
        this.description = description;
        this.post = post;
        post.getComments().add(this);
        this.user = user;
        user.getComments().add(this);
    }

}
