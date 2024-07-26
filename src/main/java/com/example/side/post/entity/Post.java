package com.example.side.post.entity;

import com.example.side.common.BaseEntity;
import com.example.side.comment.entity.Comments;
import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
public abstract class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Comments> comments = new ArrayList<>();

    /**
     * 연관관계 메서드
     */
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        user.getPosts().add(this);
    }

    /**
     * Post 수정
     */
    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
