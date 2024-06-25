package com.example.side.controller;

import com.example.side.model.entity.Notification;
import com.example.side.repository.NotificationRepository;
import com.example.side.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notification")
public class NotificationController {

}