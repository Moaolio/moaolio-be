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
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public NotificationController(NotificationRepository notificationRepository, NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return (List<Notification>) notificationService.selectAll();
    }

    @GetMapping("/{username}")
    public List<Notification> getNotificationsByUsername(@PathVariable String username) {
        // 이 메소드는 NotificationRepository에 새로운 메소드를 추가하여 구현해야 합니다.
        // 예: return notificationRepository.findByUsername(username);
    }
}