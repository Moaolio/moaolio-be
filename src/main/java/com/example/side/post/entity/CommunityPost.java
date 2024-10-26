package com.example.side.post.entity;

import com.example.side.post.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CommunityPost extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String imgUrl;

    @Builder
    public CommunityPost(String title, String description, Category category, String imgUrl) {
        super(title, description, null);
        this.category = category;
        this.imgUrl = imgUrl;  // null 대신 파라미터 값 사용
    }

    public void update(String title, String description, Category category) {
        super.setTitle(title);
        super.setDescription(description);
        this.category = category;
    }

    // 카테고리 설정 시 양방향 관계 처리를 위한 메서드
    public void setCategory(Category category) {
        this.category = category;
        if (category != null && !category.getCommunityPosts().contains(this)) {
            category.getCommunityPosts().add(this);
        }
    }
}