package com.example.side;

import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.service.PortfolioPostService;
import com.example.side.config.UserDetailsImpl;
import com.example.side.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.test.context.support.WithMockUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioPostServiceTest {

    @Autowired
    private PortfolioPostService portfolioPostService;

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    public void testCreatePostAsUser1() {
        PortfolioPostRequest postRequest = new PortfolioPostRequest();
        postRequest.setDescription("Test content");

        User user = new User();
        user.setUsername("test");
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        portfolioPostService.createPost(postRequest, userDetails);

        // 여기서 user1이 작성한 것으로 테스트할 수 있음
    }
}