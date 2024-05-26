package com.example.side.service.Impl;

import com.example.side.model.entity.Notification;
import com.example.side.repository.NotificationRepository;
import com.example.side.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> selectAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> selectOneById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification insertNotification(Notification notification) {
        return notificationRepository.save(notification);
    }


    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
