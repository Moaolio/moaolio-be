package com.example.side.post.entity;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.user.entity.User;
import com.example.side.post.dto.request.CommunityPostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*

 */
@Getter
@Setter
@Entity
public class CommunityPost extends Post{
    private String category; // 게시글 카테고리
    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(
            name = "portfolio_post_tags",
            joinColumns = @JoinColumn(name = "portfolio_post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<PostTag> tags;
    public CommunityPost() {}
    public CommunityPost(CommunityPostRequest communityPostRequest, User user) {
        super(communityPostRequest.getTitle(), communityPostRequest.getDescription(), user);
        this.category = communityPostRequest.getCategory();
        this.setDescription(communityPostRequest.getDescription());
    }
    public void update(CommunityPostRequest communityPostRequest) {
        super.setTitle(communityPostRequest.getTitle());
        super.setDescription(communityPostRequest.getDescription());
        this.category = communityPostRequest.getCategory();
    }
}
