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
    @ManyToOne
    private Category category;
    @Builder
    public CommunityPost(String title, String description, Category category) {
        super(title, description, null);
        this.category = category;
    }

    public void update(String title, String description, Category category) {
        super.setTitle(title);
        super.setDescription(description);
        this.category = category;
    }
}
