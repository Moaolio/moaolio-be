package com.example.side.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/googleLogin")
    @ResponseBody
    public String googleLogin() {

        return "";
    }
}
