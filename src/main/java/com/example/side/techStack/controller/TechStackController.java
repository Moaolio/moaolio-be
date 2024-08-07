package com.example.side.techStack.controller;

import com.example.side.techStack.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tech-stacks")
public class TechStackController {
    @Autowired
    private TechStackService techStackService;

    @PostMapping("/add")
    public ResponseEntity<String> addTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.addTechStack(userId, tech);
            return ResponseEntity.ok("분야 생성에 성공하였습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.updateTechStack(userId, tech);
            return ResponseEntity.ok("분야 수정이 성공하였습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.deleteTechStack(userId, tech);
            return ResponseEntity.ok("분야 삭제가 성공하였습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Other endpoints such as removeTechStack, getTechStacks, etc.
}
