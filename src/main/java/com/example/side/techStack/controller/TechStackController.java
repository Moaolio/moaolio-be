package com.example.side.techStack.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.techStack.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tech-stacks")
public class TechStackController {

    @Autowired
    private TechStackService techStackService;

    @PostMapping("/add")
    public List<GlobalResDto<Object>> addTechStack(@RequestParam Long userId, @RequestParam String tech) {
        List<GlobalResDto<Object>> responseList = new ArrayList<>();

        try {
            // 기술 스택을 추가하는 서비스 호출
            techStackService.addTechStack(userId, tech);

            // 성공 응답 생성
            GlobalResDto<Object> successResponse = GlobalResDto.success("기술스택 추가에 성공하였습니다.", "Success");
            responseList.add(successResponse);
        } catch (IllegalStateException e) {
            GlobalResDto<Object> errorResponse = GlobalResDto.fail(400, null, e.getMessage());
            responseList.add(errorResponse);
        } catch (IllegalArgumentException e) {
            GlobalResDto<Object> errorResponse = GlobalResDto.fail(404, null, e.getMessage());
            responseList.add(errorResponse);
        }

        return responseList;
    }

    @PutMapping("/update")
    public List<GlobalResDto<Object>> updateTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.updateTechStack(userId, tech);
            GlobalResDto<Object> successResponse = GlobalResDto.success("기술스택 수정에 성공하였습니다.", "Success");
            return List.of(successResponse);
        } catch (IllegalArgumentException e) {
            GlobalResDto<Object> errorResponse = GlobalResDto.fail(404, null, e.getMessage());
            return List.of(errorResponse);
        }

    }

    @DeleteMapping("/delete")
    public List<GlobalResDto<Object>> deleteTechStack(@RequestParam Long userId, @RequestParam String tech) {
        try {
            techStackService.deleteTechStack(userId, tech);
            GlobalResDto<Object> successResponse = GlobalResDto.success("기술스택 삭제에 성공하였습니다.", "Success");
            return List.of(successResponse);
        } catch (IllegalArgumentException e) {
            GlobalResDto<Object> errorResponse = GlobalResDto.fail(404, null, e.getMessage());
            return List.of(errorResponse);
        }
    }

}
