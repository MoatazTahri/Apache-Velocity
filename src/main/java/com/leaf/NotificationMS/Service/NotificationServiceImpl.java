package com.leaf.NotificationMS.Service;

import com.leaf.NotificationMS.Entity.NotificationEntity;
import com.leaf.NotificationMS.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationEntity addNotification(NotificationEntity notification) {
        return notificationRepository.save(notification);
    }

}
