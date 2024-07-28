package com.example.side.post.tag.entity;

import com.example.side.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PostTag {
    // Getter 및 Setter
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}
