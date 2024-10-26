package com.example.side.post.category;

import com.example.side.post.entity.CommunityPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category_mapping")
public class CategoryMapping {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private CommunityPost communityPost;


    public CategoryMapping(Category category, CommunityPost communityPost) {
        this.category = category;
        this.communityPost = communityPost;
    }
}
