package com.example.side.model.entity;

import com.example.side.request.PostRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_post")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
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
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private String category;
    private Long likeCount;
    private Long viewCount;
    //1. 포트폴리오 전용 2. 커뮤니티 전용
    private Long postType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "post")
    private List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostFile> postFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTag> tags = new ArrayList<>();


    public Post(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.tag = postRequest.getTag();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }

    public Post() {

    }

    public Post(PostRequest postRequest, User user) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.user = user;
    }



    public void update(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.tag = postRequest.getTag();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }
    public void setTags(List<PostTag> tags) {
        this.tags = tags;
    }
}
