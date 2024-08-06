package com.example.side.email.controller;

import com.example.side.email.service.EmailService;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
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

        return "Registration successful! Please check your email to verify.";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        User user = userRepository.findByVerificationToken(token);

        if (user != null && user.getVerificationTokenExpiry().isAfter(LocalDateTime.now())) {
            user.setEnabled(true);
            user.setVerificationToken(null);
            user.setVerificationTokenExpiry(null);
            userRepository.save(user);
            return "Email successfully verified!";
        } else {
            return "Invalid or expired token.";
        }
    }
}
