package com.example.side.email.controller;

import com.example.side.email.service.EmailService;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(false);  // Set as not enabled until verified
        userRepository.save(user);

        emailService.sendVerificationEmail(user);

        return "전송 완료되었습니다. 이메일을 확인해주세요.";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        User user = userRepository.findByVerificationToken(token);

        if (user != null && user.getVerificationTokenExpiry().isAfter(LocalDateTime.now())) {
            user.setEnabled(true);
            user.setVerificationToken(null);
            user.setVerificationTokenExpiry(null);
            userRepository.save(user);
            return "이메일 인증되었습니다.";
        } else {
            return "인증 실패";
        }
    }
}
