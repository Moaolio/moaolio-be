package com.example.side.post.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.config.UserDetailsImpl;
import com.example.side.post.dto.request.CommunityPostRequest;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.CommunityPostResponse;
import com.example.side.post.dto.response.PortfolioPostResponse;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.service.CommunityPostService;
import com.example.side.post.service.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PortfolioPostService portfolioPostService;
    private final CommunityPostService communityPostService;


    public PostController(PortfolioPostService portfolioPostService, CommunityPostService communityPostService) {
        this.portfolioPostService = portfolioPostService;
        this.communityPostService = communityPostService;
    }

    // 포트폴리오 게시글 생성
    @PostMapping("/create/portfolio")
    public GlobalResDto<PortfolioPostResponse> createPortfolioPost(@RequestBody PortfolioPostRequest portfolioPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PortfolioPostResponse response = portfolioPostService.createPost(portfolioPostRequest, userDetails);
        return GlobalResDto.success(response, "게시글이 생성되었습니다.");
    }

    // 커뮤니티 게시글 생성
    @PostMapping("/create/community")
    public GlobalResDto<CommunityPostResponse> createCommunityPost(@RequestBody CommunityPostRequest communityPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommunityPostResponse response = communityPostService.createPost(communityPostRequest, userDetails);
        return GlobalResDto.success(response, "게시글이 성공적으로 생성되었습니다.");
    }

    // 포트폴리오 게시글 수정
    @PutMapping("/update/portfolio/{postId}")
    public GlobalResDto<PortfolioPostResponse> updatePortfolioPost(@PathVariable Long postId, @RequestBody PortfolioPostRequest portfolioPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PortfolioPostResponse response = portfolioPostService.updatePost(postId, portfolioPostRequest, userDetails);
        return GlobalResDto.success(response, "게시글이 성공적으로 수정되었습니다.");
    }

    // 커뮤니티 게시글 수정
    @PutMapping("/update/community/{postId}")
    public GlobalResDto<CommunityPostResponse> updateCommunityPost(@PathVariable Long postId, @RequestBody CommunityPostRequest communityPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommunityPostResponse response = communityPostService.updatePost(postId, communityPostRequest, userDetails);
        return GlobalResDto.success(response, "커뮤니티 게시글이 성공적으로 수정되었습니다.");
    }

    // 포트폴리오 게시글 삭제
    @DeleteMapping("/delete/portfolio/{postId}")
    public GlobalResDto<Long> deletePortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        portfolioPostService.deletePost(postId, userDetails);
        return GlobalResDto.success(postId, "포트폴리오 게시글이 성공적으로 삭제되었습니다.");
    }

    // 커뮤니티 게시글 삭제
    @DeleteMapping("/delete/community/{postId}")
    public GlobalResDto<Long> deleteCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        communityPostService.deletePost(postId, userDetails);
        return GlobalResDto.success(postId, "커뮤니티 게시글이 성공적으로 삭제되었습니다.");
    }

    // 포트폴리오 게시글 조회 (최신순)
    @GetMapping("/get/portfolio")
    public GlobalResDto<List<PortfolioPostResponse>> recentPortfolioPosts() {
        List<PortfolioPostResponse> response = portfolioPostService.portfolioPosts();
        return GlobalResDto.success(response, "포트폴리오 게시글 목록 조회가 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 조회 (최신순)
    @GetMapping("/get/community")
    public GlobalResDto<List<CommunityPostResponse>> recentCommunityPosts() {
        List<CommunityPostResponse> response = communityPostService.communityPosts();
        return GlobalResDto.success(response, "커뮤니티 게시글 목록 조회가 성공적으로 완료되었습니다.");
    }

    // 포트폴리오 게시글 상세 조회
    @GetMapping("/get/portfolio/{postId}")
    public GlobalResDto<PortfolioPostResponse> getPortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PortfolioPostResponse response = portfolioPostService.getPostId(postId, userDetails.getUser());
        return GlobalResDto.success(response, "포트폴리오 게시글 조회가 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 상세 조회
    @GetMapping("/get/community/{postId}")
    public GlobalResDto<CommunityPostResponse> getCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommunityPostResponse response = communityPostService.getPostId(postId, userDetails.getUser());
        return GlobalResDto.success(response, "커뮤니티 게시글 조회가 성공적으로 완료되었습니다.");
    }

    // 포트폴리오 게시글 필터링 조회
    @GetMapping("/portfolio/search")
    public GlobalResDto<List<PortfolioPost>> searchPortfolioPosts(
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "likes") String sortBy) {
        List<PortfolioPost> response = portfolioPostService.getFilteredPosts(tags, startDate, endDate, sortBy);
        return GlobalResDto.success(response, "포트폴리오 게시글 필터링 조회가 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 검색
    @GetMapping("/community/search")
    public GlobalResDto<List<CommunityPostResponse>> searchCommunityPostsByTitle(@RequestParam(required = false) String title) {
        List<CommunityPostResponse> response = communityPostService.searchPostsByTitle(title);
        return GlobalResDto.success(response, "커뮤니티 게시글 검색이 성공적으로 완료되었습니다.");
    }

    // 포트폴리오 게시글 검색
    @GetMapping("/portfolio/search")
    public GlobalResDto<List<PortfolioPost>> searchPortfolioPosts(@RequestParam(required = false) String keyword) {
        List<PortfolioPost> response = portfolioPostService.searchPosts(keyword);
        return GlobalResDto.success(response, "포트폴리오 게시글 검색이 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 카테고리별 조회
    @GetMapping("/community/category")
    public GlobalResDto<List<CommunityPostResponse>> findCommunityPostsByCategory(@RequestParam(required = false) String category) {
        List<CommunityPostResponse> response = communityPostService.findByCategory(category);
        return GlobalResDto.success(response, "카테고리별 커뮤니티 게시글 조회가 성공적으로 완료되었습니다.");
    }
    //유저신고



//    //커뮤니티 게시글 스크랩
//    @PostMapping("/community/scrap/{postId}")
//    public HashMap<String, Long> scrapCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.scrapPost(postId, userDetails);
//    }
//    //커뮤니티 게시글 스크랩 취소
//    @DeleteMapping("/community/scrap/{postId}")
//    public HashMap<String, Long> unscrapCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.unscrapPost(postId, userDetails);
//    }
//    //커뮤니티 게시글 스크랩 조회
//    @GetMapping("/community/scrap")
//    public List<CommunityPostResponse> scrapedCommunityPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.scrapedPosts(userDetails);
//    }
//
//
//
//    //포트폴리오 게시물 스크랩
//    @PostMapping("/portfolio/scrap/{postId}")
//    public HashMap<String, Long> scrapPortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.scrapPost(postId, userDetails);
//    }
//    //포트폴리오 게시물 스크랩 취소
//    @DeleteMapping("/portfolio/scrap/{postId}")
//    public HashMap<String, Long> unscrapPortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.unscrapPost(postId, userDetails);
//    }
//    //포트폴리오 게시물 스크랩 조회
//    @GetMapping("/portfolio/scrap")
//    public List<PortfolioPostResponse> scrapedPortfolioPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.scrapedPosts(userDetails);
//    }
//


}
