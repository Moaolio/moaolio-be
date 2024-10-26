package com.example.side.post.category;

import com.example.side.post.entity.CommunityPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "name", length = 16, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CommunityPost> communityPosts = new ArrayList<>();


    public Category(String name) {
        this.name = name;
    }

    public void addCommunityPost(CommunityPost communityPost) {
        this.communityPosts.add(communityPost);
        if (communityPost.getCategory() != this) {
            communityPost.setCategory(this);
        }
    }

    public List<CommunityPost> getCommunityPosts() {
        return communityPosts;
    }
}
