package com.example.side.post.category;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@AllArgsConstructor
public class CategoryInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(Arrays.asList(
                    new Category("자유게시판"),
                    new Category("백엔드"),
                    new Category("프론트엔드"),
                    new Category("디자인"),
                    new Category("데브옵스"),
                    new Category("AI")
            ));
        }

    }
}
