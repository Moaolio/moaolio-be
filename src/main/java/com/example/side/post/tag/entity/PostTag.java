package com.example.side.post.tag.entity;

import com.example.side.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "tags")
public class PostTag {
    // Getter 및 Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}
