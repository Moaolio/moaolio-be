package com.example.side.comment.entity;

import com.example.side.common.BaseEntity;
import com.example.side.post.entity.Post;
import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"post", "user"})
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
    @JoinColumn(name = "member_id")
    private User user;

    /**
     * 연관관계 메서드
     */
    public Comments(String description, Post post, User user) {
        this.description = description;
        this.post = post;
        post.getComments().add(this);
        this.user = user;
        user.getComments().add(this);
    }

}
