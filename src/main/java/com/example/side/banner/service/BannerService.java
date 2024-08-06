package com.example.side.banner.service;

import com.example.side.banner.entitiy.Banner;
import com.example.side.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;

    public List<Banner> getBannerList() {
        return bannerRepository.findAll();
    }

}
