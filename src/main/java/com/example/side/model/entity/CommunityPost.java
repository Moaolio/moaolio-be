package com.example.side.model.entity;

import com.example.side.request.PostRequest;

public class CommunityPost extends Post{
    private String communitySpecificField; // 커뮤니티 포스트에 특화된 필드 예시

    public CommunityPost(PostRequest postRequest, User user) {
        super(postRequest, user);
        this.communitySpecificField = postRequest.getCommunitySpecificField();
    }
}
