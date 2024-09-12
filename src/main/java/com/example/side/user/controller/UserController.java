package com.example.side.user.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.user.dto.request.UidFindRequest;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.request.UserUpdateRequest;
import com.example.side.user.dto.response.*;
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

    @PatchMapping("/update")
    public GlobalResDto<Object> update(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserUpdateResponse userUpdateResponse = userService.update(userUpdateRequest);

        return GlobalResDto.success(userUpdateResponse, "회원정보 수정 성공");
    }

}
