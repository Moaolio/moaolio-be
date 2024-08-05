package com.example.side.user.controller;

import com.example.side.common.DefaultApiResponse;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public DefaultApiResponse<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        UserSignUpResponse userSignUpResponse = userService.signUp(userSignUpRequest);
        return DefaultApiResponse.success(userSignUpResponse);
    }
}
