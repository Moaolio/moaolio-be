package com.example.side.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "user_post")
@lombok.Getter
@lombok.Setter
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String tag;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private Long likeCount;
    private Long viewCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "userPost", fetch = FetchType.LAZY)
    private List<UserPostComments> userPostComments;

    @OneToMany(mappedBy = "userPost", fetch = FetchType.LAZY)
    private List<UserPostFile> userPostFiles;
}
