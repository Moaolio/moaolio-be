package com.example.side.model.entity;

import com.example.side.request.PostRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "user_post")
@lombok.Getter
@lombok.Setter
public class Post {
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
    //1. 포트폴리오 전용 2. 커뮤니티 전용
    private Long postType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comments> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostFile> postFiles;


    public Post(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.tag = postRequest.getTag();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }

    public Post() {

    }


    public void update(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.tag = postRequest.getTag();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }
}
