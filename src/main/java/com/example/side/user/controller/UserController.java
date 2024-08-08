package com.example.side.user.controller;

import com.example.side.common.DefaultApiResponse;
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
    public DefaultApiResponse<UserSignUpResponse> signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        UserSignUpResponse userSignUpResponse = userService.signUp(userSignUpRequest);
        return DefaultApiResponse.success(userSignUpResponse);
    }

    @PostMapping("/findId")
    public DefaultApiResponse<UsernameFindResponse> findId(@RequestBody UsernameFindRequest usernameFindRequest) {
        UsernameFindResponse usernameFindResponse = userService.findUsername(usernameFindRequest);
        return DefaultApiResponse.success(usernameFindResponse);
    }

    @PostMapping("/findPassword")
    public DefaultApiResponse<UserPasswordFindResponse> findPassword(@RequestBody UserPasswordFindRequest userPasswordFindRequest) {
        UserPasswordFindResponse userPasswordFindResponse = userService.findPassword(userPasswordFindRequest);
        return DefaultApiResponse.success(userPasswordFindResponse);
    }
}
