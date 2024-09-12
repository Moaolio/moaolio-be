package com.example.side.post.service;

import com.example.side.Exception.CustomException;
import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.comments.entity.Comments;
import com.example.side.config.UserDetailsImpl;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.like.entity.PostLike;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.PortfolioPostResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.side.Exception.ErrorCode.NOT_FOUND_POST;

@RequiredArgsConstructor
@Service
public class PortfolioPostService {

    private static final String NO_PERMISSION = "이 페이지에 대한 권한이 없습니다.";
    private static final String UNKNOWN_POST_ID = "알 수 없는 게시물 ID: ";

    private final PortfolioPostRepository portfolioPostRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    // 생성
    @Transactional
    public PortfolioPostResponse createPost(PortfolioPostRequest portfolioPostRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = new PortfolioPost(portfolioPostRequest, userDetails.getUser());
        PortfolioPost savedPortfolioPost = portfolioPostRepository.save(portfolioPost);
        return new PortfolioPostResponse(savedPortfolioPost);
    }

    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long postId, PortfolioPostRequest postRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = getPostById(postId);

        validateUserPermission(portfolioPost, userDetails);

        portfolioPost.update(postRequest);
        PortfolioPost updatedPortfolioPost = portfolioPostRepository.save(portfolioPost);
        return new PortfolioPostResponse(updatedPortfolioPost);
    }

    // 삭제
    @Transactional
    public HashMap<String, Long> deletePost(Long postId, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = getPostById(postId);

        validateUserPermission(portfolioPost, userDetails);

        portfolioPostRepository.deleteById(postId);
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", portfolioPost.getId());
        return responseId;
    }

    // 전체 조회
    public List<PortfolioPostResponse> portfolioPosts() {
        return portfolioPostRepository.findAll().stream()
                .map(PortfolioPostResponse::new)
                .collect(Collectors.toList());
    }

    // 상세 조회
    public PortfolioPostResponse getPostId(Long postId, User user) {
        PortfolioPost portfolioPost = getPostById(postId);
        List<CommentsResponse> commentsResponses = getSortedComments(portfolioPost);
        boolean isLiked = isPostLikedByUser(postId, user);
        return PortfolioPostResponse.of(portfolioPost, commentsResponses, isLiked);
    }

    // 필터링 조회
    public List<PortfolioPost> getFilteredPosts(List<String> tags, LocalDateTime startDate, LocalDateTime endDate, String sortBy) {
        return portfolioPostRepository.findPosts(tags, startDate, endDate, sortBy);
    }

    // 검색
    public List<PortfolioPost> searchPosts(String keyword) {
        return portfolioPostRepository.findByTitleContaining(keyword);
    }

    // 유틸 메소드: 게시물 ID로 찾기
    private PortfolioPost getPostById(Long postId) {
        return portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));
    }

    // 유틸 메소드: 유저 권한 검증
    private void validateUserPermission(PortfolioPost portfolioPost, UserDetailsImpl userDetails) {
        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException(NO_PERMISSION);
        }
    }

    // 유틸 메소드: 댓글 정렬 및 응답 생성
    private List<CommentsResponse> getSortedComments(PortfolioPost portfolioPost) {
        return portfolioPost.getComments().stream()
                .sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
                .map(CommentsResponse::new)
                .collect(Collectors.toList());
    }

    // 유틸 메소드: 게시물 좋아요 여부 확인
    private boolean isPostLikedByUser(Long postId, User user) {
        return postLikeRepository.findByPostIdAndUserId(postId, user.getId()) != null;
    }
}
