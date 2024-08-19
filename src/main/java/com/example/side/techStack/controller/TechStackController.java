package com.example.side.techStack.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.techStack.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tech-stacks")
public class TechStackController {

    @Autowired
    private TechStackService techStackService;

    @PostMapping("/add")
    public GlobalResDto<Object> addTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.addTechStack(userId, tech);
            return GlobalResDto.success("분야 생성에 성공하였습니다.", "Success");
        } catch (IllegalStateException e) {
            return GlobalResDto.fail(400, null, e.getMessage());
        } catch (IllegalArgumentException e) {
            return GlobalResDto.fail(404, null, e.getMessage());
        }
    }

    @PutMapping("/update")
    public GlobalResDto<Object> updateTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.updateTechStack(userId, tech);
            return GlobalResDto.success("분야 수정이 성공하였습니다.", "Success");
        } catch (IllegalStateException e) {
            return GlobalResDto.fail(400, null, e.getMessage());
        } catch (IllegalArgumentException e) {
            return GlobalResDto.fail(404, null, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public GlobalResDto<Object> deleteTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.deleteTechStack(userId, tech);
            return GlobalResDto.success("분야 삭제가 성공하였습니다.", "Success");
        } catch (IllegalArgumentException e) {
            return GlobalResDto.fail(404, null, e.getMessage());
        }
    }

    // Other endpoints such as removeTechStack, getTechStacks, etc.
}
