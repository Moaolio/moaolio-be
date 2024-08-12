package com.example.side.post.entity;

import com.example.side.comments.entity.Comments;
import com.example.side.common.BaseEntity;
import com.example.side.post.PostStatus;
import com.example.side.post.file.entity.PostFile;
import com.example.side.post.tag.entity.PostTag;
import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Column(nullable = false)
    private String tag;

    // Description 설정 메서드
    @Column(nullable = false)
    @Getter @Setter
    private String description;

    @Getter @Setter
    private Long likeCount = 0L;

    @Getter @Setter
    private Long viewCount = 0L;

    // 1. 포트폴리오 전용 2. 커뮤니티 전용
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private PostStatus postType;

    @CreatedDate
    @Getter @Setter
    private String createdAt;

    @LastModifiedDate
    @Getter @Setter
    private String updatedAt;





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private List<PostFile> postFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter
    private Set<PostTag> postTags;


    // 커스텀 생성자
    public Post(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

}
