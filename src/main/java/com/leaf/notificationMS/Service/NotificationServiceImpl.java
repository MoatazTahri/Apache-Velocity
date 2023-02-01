package com.leaf.notificationMS.Service;

import com.leaf.notificationMS.Entity.NotificationEntity;
import com.leaf.notificationMS.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public NotificationEntity addNotification(NotificationEntity notification) {
        return notificationRepository.save(notification);
    }

}
