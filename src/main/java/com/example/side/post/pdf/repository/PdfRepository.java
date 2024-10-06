package com.example.side.post.pdf.repository;

import com.example.side.post.pdf.entity.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfRepository extends JpaRepository<Pdf, Long> {
    String findPathById(Long id);
}
