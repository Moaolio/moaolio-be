package com.example.side.post.entity;

import com.example.side.post.category.Category;
import com.example.side.user.entity.User;
import com.example.side.post.dto.request.CommunityPostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*

 */
@Getter
@Setter
@Entity
@DiscriminatorValue("CommunityPost")
public class CommunityPost extends Post {

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    public CommunityPost() {}
//
//    public CommunityPost(String title, String description, User user, Category category) {
//        super(title, description, user);
//        this.category = category;
//    }
//
//    public void update(String title, String description, Category category) {
//        super.setTitle(title);
//        super.setDescription(description);
//        this.category = category;
//    }
}
