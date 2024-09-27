package com.example.side.post.file.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.post.file.service.PostFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@Slf4j(topic = "PostFileController")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostFileController {
    private final PostFileService postFileService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public GlobalResDto<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return GlobalResDto.fail(400, null, "파일이 비어있습니다.");
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

            return GlobalResDto.success(fileDownloadUri, "파일 업로드 성공");

        } catch (IOException e) {
            log.error("File upload error", e);
            return GlobalResDto.fail(500, null, "서버 에러");
        }
    }
}
