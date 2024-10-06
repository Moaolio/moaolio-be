package com.example.side.post.pdf.controller;

import com.example.side.post.pdf.repository.PdfRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@RestController
@RequestMapping("/api/pdf")
@AllArgsConstructor
public class PdfController {
    private final PdfRepository pdfRepository;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getPDF(@PathVariable Long id) throws IOException, IOException {
        // id로 파일 경로 조회
        String filePath = pdfRepository.findPathById(id);

        // 파일 읽기
        byte[] contents = Files.readAllBytes(Paths.get(filePath));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "file.pdf");

        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
