package com.example.springvelocity.Service.Notification;

import com.example.springvelocity.Entity.Notification;
import com.example.springvelocity.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsWhereRecipient(String address) {
        return notificationRepository.findAllByRecipientAddress(address);
    }
}
