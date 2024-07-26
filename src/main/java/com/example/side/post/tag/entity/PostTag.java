package com.example.side.post.tag.entity;

import com.example.side.post.entity.PortfolioPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<PortfolioPost> posts;

    // 기본 생성자
    public PostTag() {}

    // 커스텀 생성자
    public PostTag(String name) {
        this.name = name;
    }
}
