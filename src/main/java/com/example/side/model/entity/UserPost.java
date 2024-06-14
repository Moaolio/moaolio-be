package com.example.side.model.entity;

import com.example.side.request.UserPostRequest;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "user_post")
@lombok.Getter
@lombok.Setter

public class UserPost{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String tag;
    @Column(nullable = false)
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private Long likeCount;
    private Long viewCount;
    private Long postId;

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

    public UserPost(UserPostRequest userPostRequest) {
        this.title = userPostRequest.getTitle();
        this.tag = userPostRequest.getTag();
        this.content = userPostRequest.getContent();
        this.category = userPostRequest.getCategory();
    }

    public UserPost() {

    }


    public void update(UserPostRequest userPostRequest) {
        this.title = userPostRequest.getTitle();
        this.tag = userPostRequest.getTag();
        this.content = userPostRequest.getContent();
        this.category = userPostRequest.getCategory();
    }
}
