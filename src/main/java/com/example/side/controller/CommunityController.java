package com.example.side.controller;

import com.example.side.model.entity.Community;
import com.example.side.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냄
@RequestMapping("/api/v1/communities") // 기본 URL 경로를 설정
public class CommunityController {

    @Autowired // CommunityService 빈을 자동으로 주입
    private CommunityService communityService;

    /**
     * 모든 커뮤니티를 조회
     * @return 모든 커뮤니티의 리스트
     */
    @GetMapping
    public List<Community> getAllCommunities() {
        return communityService.findAll();
    }

    /**
     * 특정 ID의 커뮤니티를 조회
     * @param id 조회할 커뮤니티의 ID
     * @return 커뮤니티가 존재하면 해당 커뮤니티 객체, 없으면 404 상태 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        Optional<Community> community = communityService.findById(id);
        if (community.isPresent()) {
            return ResponseEntity.ok(community.get()); // 200 OK와 커뮤니티 객체 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 반환
        }
    }

    /**
     * 특정 직종의 커뮤니티들을 조회
     * @param occupation 조회할 직종
     * @return 해당 직종의 커뮤니티 리스트
     */
    @GetMapping("/occupation/{occupation}")
    public List<Community> getCommunitiesByOccupation(@PathVariable String occupation) {
        return communityService.findByOccupation(occupation);
    }

    /**
     * 새로운 커뮤니티를 생성
     * @param community 생성할 커뮤니티 객체
     * @return 생성된 커뮤니티 객체
     */
    @PostMapping
    public Community createCommunity(@RequestBody Community community) {
        return communityService.save(community);
    }

    /**
     * 특정 ID의 커뮤니티를 업데이트
     * @param id 업데이트할 커뮤니티의 ID
     * @param communityDetails 업데이트할 커뮤니티의 상세 정보
     * @return 업데이트된 커뮤니티 객체 또는 404 상태 반환
     */
    @PutMapping("/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable Long id, @RequestBody Community communityDetails) {
        Optional<Community> community = communityService.findById(id);
        if (community.isPresent()) {
            Community updatedCommunity = community.get();
            updatedCommunity.setOccupation(communityDetails.getOccupation());
            return ResponseEntity.ok(communityService.save(updatedCommunity)); // 200 OK와 업데이트된 커뮤니티 객체 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 반환
        }
    }

    /**
     * 특정 ID의 커뮤니티를 삭제
     * @param id 삭제할 커뮤니티의 ID
     * @return 204 상태 코드 또는 404 상태 코드 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        if (communityService.findById(id).isPresent()) {
            communityService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content 반환
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 반환
        }
    }
}
