package com.example.side.post.category;

import com.example.side.post.entity.CommunityPost;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 16, nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<CommunityPost> posts = new ArrayList<>();



    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}
