package com.example.side.service;

import com.example.side.model.entity.Notification;

import java.util.Optional;

public interface NotificationService {
    Iterable<Notification> selectAll();
    Optional<Notification> selectOneById(Long id);
    Notification insertNotification(Notification notification);
    void deleteNotification(Long id);
}
