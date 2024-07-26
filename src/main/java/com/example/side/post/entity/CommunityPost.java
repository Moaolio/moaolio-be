package com.example.side.post.entity;

import com.example.side.post.dto.request.CommunityPostRequest;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CommunityPost extends Post {


}
