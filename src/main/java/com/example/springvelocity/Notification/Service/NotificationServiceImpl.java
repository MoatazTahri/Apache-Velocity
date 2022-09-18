package com.example.springvelocity.Notification.Service;

import com.example.springvelocity.Notification.Entity.Notification;
import com.example.springvelocity.Notification.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

}
