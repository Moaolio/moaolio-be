package com.example.side.user.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.common.DefaultApiResponse;
import com.example.side.common.exception.UserNotFoundException;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.request.UsernameFindRequest;
import com.example.side.user.dto.response.UserPasswordFindResponse;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.dto.response.UsernameFindResponse;
import com.example.side.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public GlobalResDto<Object> signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        UserSignUpResponse userSignUpResponse = userService.signUp(userSignUpRequest);
        return GlobalResDto.success(userSignUpResponse, "회원가입 성공");
    }

    @PostMapping("/findId")
    public GlobalResDto<Object> findId(@RequestBody UsernameFindRequest usernameFindRequest) {
        UsernameFindResponse usernameFindResponse = userService.findUsername(usernameFindRequest);
        return GlobalResDto.success(usernameFindResponse, "아이디 찾기 성공");
    }

    @PostMapping("/findPassword")
    public GlobalResDto<Object> findPassword(@RequestBody UserPasswordFindRequest userPasswordFindRequest) {
        UserPasswordFindResponse userPasswordFindResponse = userService.findPassword(userPasswordFindRequest);
        return GlobalResDto.success(userPasswordFindResponse, "비밀번호 찾기 성공");
    }

    @PostMapping("/idCheck")
    public GlobalResDto<Object> idCheck(@RequestBody UsernameFindRequest usernameFindRequest) throws UserNotFoundException {
        boolean isAvailable = true;
        try {
            UsernameFindResponse usernameFindResponse = userService.findUsername(usernameFindRequest);
            if (!(usernameFindResponse.getUsername().isBlank())) {
                isAvailable = false;
            }
        } catch (UserNotFoundException e) {
            return GlobalResDto.success(isAvailable, "사용가능한 아이디입니다.");
        }
        return GlobalResDto.success(isAvailable, "이미 존재하는 아이디입니다.");
    }

}
