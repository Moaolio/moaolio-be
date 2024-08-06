package com.example.side.post.file.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/files")
@Slf4j(topic = "PostFileController")
@RestController // Spring Boot's @RestController annotation for returning JSON responses.
@CrossOrigin(origins = "*") // Cors configuration for enabling Cross-Origin Resource Sharing (CORS).
public class PostFileController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 비어있습니다.");
        }

        try {
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(file.getOriginalFilename());
            file.transferTo(filePath);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(filePath.getFileName().toString())
                    .toUriString();
            return ResponseEntity.ok(fileDownloadUri);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버 에러");
        }
    }
}
