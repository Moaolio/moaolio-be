package com.example.side.post.link.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RequestMapping("/api/link")
@RestController
public class LinkController {
    @GetMapping("/iframe-page")
    public String iframePage(@RequestParam(required = false) String url, Model model) {
        model.addAttribute("url", url);
        return "iframe-page";
    }
}
