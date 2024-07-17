package com.example.side.post.entity;

import com.example.side.post.dto.request.UpdatePortfolioPostDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("P")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PortfolioPost extends Post {
    private String filePath;

    public void changePortfolioPost(UpdatePortfolioPostDto updatePortfolioPostDto) {
        super.updatePost(updatePortfolioPostDto.getTitle(), updatePortfolioPostDto.getContent());
        this.filePath = updatePortfolioPostDto.getFilepath();
    }

}
