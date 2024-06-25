package com.example.side.controller;

import com.example.side.model.entity.Community;
import com.example.side.repository.CommunityRepository;
import com.example.side.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/community")
public class CommunityController {
}