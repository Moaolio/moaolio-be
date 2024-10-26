package com.example.side.post.service;

import com.example.side.Exception.CustomException;
import com.example.side.auth.CustomUserDetails;
import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.entity.PostType;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.post.tag.entity.PostTag;
import com.example.side.post.tag.entity.PostTagRepository;
import com.example.side.post.tag.entity.Tag;
import com.example.side.post.tag.entity.TagRepository;
import com.example.side.user.entity.User;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.PortfolioPostResponse;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.jasypt.commons.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.side.Exception.ErrorCode.NOT_FOUND_POST;

@RequiredArgsConstructor
@Service
public class PortfolioPostService {

    private static final String NO_PERMISSION = "이 페이지에 대한 권한이 없습니다.";

    private final PortfolioPostRepository portfolioPostRepository;
    private final PostLikeRepository postLikeRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final ObjectStorage objectStorageClient;

    static final String NAMESPACE = "cnshljz7n2kf";
    static final String BUCKET_NAME = "bucket-20240928-2302";

    private static final String REGION = "ap-sydney-1";


    // 생성
    @Transactional
    public PortfolioPostResponse createPost(PortfolioPostRequest portfolioPostRequest, CustomUserDetails userDetails, MultipartFile multipartFile) throws Exception {
        String imgUrl = null;
    // 이미지 업로드 오라클 클라우드버킷
        //너쫌 개같더라
        if(multipartFile != null && !multipartFile.isEmpty()){
            String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename());
            try{
                String objectName = UUID.randomUUID().toString()+"_"+fileName;
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .namespaceName(NAMESPACE)
                        .bucketName(BUCKET_NAME)
                        .objectName(objectName)
                        .putObjectBody(multipartFile.getInputStream())
                        .contentType(multipartFile.getContentType())
                        .contentLength(multipartFile.getSize())
                        .build();
                objectStorageClient.putObject(putObjectRequest);
                imgUrl = "https://objectstorage." + REGION + ".oraclecloud.com/n/" + NAMESPACE + "/b/" + BUCKET_NAME + "/o/" + objectName;
            } catch (Exception e) {
                return PortfolioPostResponse.fail("UPLOAD_FAILED", "파일 업로드에 실패했습니다: " + e.getMessage());
            }
        }


        PortfolioPost portfolioPost = PortfolioPost.builder()
                .url(portfolioPostRequest.getUrl())
                .technologyStack(portfolioPostRequest.getTechnologyStack())
                .img(imgUrl)

                .build();
        portfolioPost.setPostType(PostType.PORTFOLIO);
        portfolioPost.setTitle(portfolioPostRequest.getTitle());
        portfolioPost.setDescription(portfolioPostRequest.getDescription());
        portfolioPost.setUser(userDetails.getUser());
        portfolioPost.setCreatedAt(LocalDateTime.now());
        portfolioPost.setUpdatedAt(LocalDateTime.now());
        portfolioPost.setLikeCount(0L);
        portfolioPost.setViewCount(0L);
        portfolioPostRepository.save(portfolioPost);

        processTags(portfolioPost, portfolioPostRequest.getTags());

        return PortfolioPostResponse.from(portfolioPost);

    }

    private void processTags(PortfolioPost post, List<String> tagNames) {
        if (tagNames != null && !tagNames.isEmpty()) {
            for (String name : tagNames) {
                Tag tag = tagRepository.findByName(name)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(name);
                            return tagRepository.save(newTag);
                        });
                PostTag postTag = new PostTag();
                postTag.setPost(post);
                postTag.setTag(tag);
                postTagRepository.save(postTag);
            }
        }
    }

    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long portfolioPostId, PortfolioPostRequest portfolioPostRequest, CustomUserDetails userDetails) throws Exception {
        PortfolioPost portfolioPost = portfolioPostRepository.findById(portfolioPostId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
//        // 이미지 업데이트
//        if (portfolioPostRequest.getImg() != null) {
//            // 기존 이미지가 있다면 삭제
//            if (portfolioPost.getImg() != null) {
//                objectStorageService.deleteImage(portfolioPost.getImg());
//            }
//            String newObjectName = objectStorageService.uploadImage(portfolioPostRequest.getImg());
//            portfolioPost.setImg(newObjectName);
//        }
//        String newObjectName = objectStorageService.uploadImage(portfolioPostRequest.getImg());

        portfolioPost.setTitle(portfolioPostRequest.getTitle());
        portfolioPost.setDescription(portfolioPostRequest.getDescription());
        portfolioPost.setUrl(portfolioPostRequest.getUrl());
        portfolioPost.setTechnologyStack(portfolioPostRequest.getTechnologyStack());
        portfolioPost.setImg(portfolioPost.getImg());
        portfolioPost = portfolioPostRepository.save(portfolioPost);
        return PortfolioPostResponse.from(portfolioPost);
    }
    // 삭제
    @Transactional
    public HashMap<String, Long> deletePost(Long postId, CustomUserDetails userDetails) {
        PortfolioPost portfolioPost = getPostById(postId);

        validateUserPermission(portfolioPost, userDetails);
//        // 이미지 삭제
//        if (portfolioPost.getImg() != null) {
//            objectStorageService.deleteImage(portfolioPost.getImg());
//        }

        portfolioPostRepository.deleteById(postId);
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", portfolioPost.getId());
        return responseId;
    }
    // 전체 조회
    @Transactional(readOnly = true)
    public Page<PortfolioPostResponse> portfolioPosts(Pageable pageable) {
        Page<PortfolioPost> portfolioPostPage = portfolioPostRepository.findAll(pageable);
        return portfolioPostPage.map(PortfolioPostResponse::new);
    }
    // 상세 조회
    @Transactional
    public PortfolioPostResponse getPostId(Long postId, User user) {
        PortfolioPost portfolioPost = getPostById(postId);
        List<CommentsResponse> commentsResponses = getSortedComments(portfolioPost);
        boolean isLiked = isPostLikedByUser(postId, user);
        return PortfolioPostResponse.of(portfolioPost, commentsResponses, isLiked);
    }
    // 좋아요 순 정렬
    @Transactional(readOnly = true)
    public List<PortfolioPostResponse> likedPosts() {
        return portfolioPostRepository.findAllByOrderByLikeCountDesc().stream()
                .map(PortfolioPostResponse::new)
                .collect(Collectors.toList());
    }
    // 검색
    @Transactional(readOnly = true)
    public List<PortfolioPost> searchPostsByTitle(String keyword) {
        return portfolioPostRepository.findByTitle(keyword);
    }

    // 유틸 메소드: 게시물 ID로 찾기
    private PortfolioPost getPostById(Long postId) {
        return portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_POST));
    }

    // 유틸 메소드: 유저 권한 검증
    private void validateUserPermission(PortfolioPost portfolioPost, CustomUserDetails userDetails) {
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
    //내 게시글 조회
    @Transactional(readOnly = true)
    public Page<PortfolioPostResponse> myPosts(Pageable pageable, CustomUserDetails userDetails) {
        User user = Optional.ofNullable(userDetails.getUser())
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        Page<PortfolioPost> postPage = portfolioPostRepository.findMyPosts(user,pageable);

        return postPage.map(PortfolioPostResponse::new);
    }
    // 파일 이름 생성
    public class CommonUtils {

        public static String buildFileName(String originalFilename) {
            // 파일 확장자 추출
            String extension = "";
            int i = originalFilename.lastIndexOf('.');
            if (i > 0) {
                extension = originalFilename.substring(i);
            }

            // UUID를 사용하여 고유한 파일 이름 생성
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            return uniqueFileName;
        }
    }
}

