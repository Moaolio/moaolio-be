package com.example.side.banner.controller;

import com.example.side.banner.entitiy.Banner;
import com.example.side.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;
    //
    @GetMapping("/list")
    public List<Banner> getBannerList() {
        return bannerService.getBannerList();
    }

}