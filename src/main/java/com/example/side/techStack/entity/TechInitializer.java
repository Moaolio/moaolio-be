package com.example.side.techStack.entity;

import com.example.side.techStack.repository.TechStackRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class TechInitializer implements CommandLineRunner {
    private final TechStackRepository techStackRepository;
    @Override
    public void run(String... args) throws Exception {
        if (techStackRepository.count()==0){
            techStackRepository.saveAll(Arrays.asList(
                    new TechStack("Java"),
                    new TechStack("JavaScript"),
                    new TechStack("React"),
                    new TechStack("Spring Boot"),
                    new TechStack("MySQL"),
                    new TechStack("MongoDB"),
                    new TechStack("Docker"),
                    new TechStack("Kubernetes"),
                    new TechStack("AWS"),
                    new TechStack("GCP"),
                    new TechStack("Azure"),
                    new TechStack("Python"),
                    new TechStack("C++"),
                    new TechStack("C#"),
                    new TechStack("Node.js"),
                    new TechStack("Vue.js"),
                    new TechStack("Angular"),
                    new TechStack("TypeScript"),
                    new TechStack("HTML"),
                    new TechStack("CSS"),
                    new TechStack("Sass"),
                    new TechStack("Less"),
                    new TechStack("Jenkins"),
                    new TechStack("Git"),
                    new TechStack("Confluence"),
                    new TechStack("Notion"),
                    new TechStack("Figma"),
                    new TechStack("Adobe XD"),
                    new TechStack("Zeplin"),
                    new TechStack("Photoshop"),
                    new TechStack("Illustrator"),
                    new TechStack("Premiere Pro"),
                    new TechStack("UI/UX Design")
            ));
        }

    }
}
