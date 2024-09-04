package com.example.side.user.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.user.dto.request.UidFindRequest;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.response.UserPasswordFindResponse;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.dto.response.UidExistResponse;
import com.example.side.user.dto.response.UidFindResponse;
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
    public GlobalResDto<Object> findId(@RequestBody UidFindRequest uidFindRequest) {
        UidFindResponse uidFindResponse = userService.findUid(uidFindRequest);
        return GlobalResDto.success(uidFindResponse, "아이디 찾기 성공");
    }

    @PostMapping("/findPassword")
    public GlobalResDto<Object> findPassword(@RequestBody UserPasswordFindRequest userPasswordFindRequest) {
        UserPasswordFindResponse userPasswordFindResponse = userService.findPassword(userPasswordFindRequest);
        return GlobalResDto.success(userPasswordFindResponse, "비밀번호 찾기 성공");
    }

    @PostMapping("/idCheck")
    public GlobalResDto<UidExistResponse> idCheck(@RequestBody UidFindRequest uidFindRequest) {
        UidExistResponse uidExistResponse = userService.existUid(uidFindRequest);

        return GlobalResDto.success(uidExistResponse, "중복 확인 성공");
    }



}
