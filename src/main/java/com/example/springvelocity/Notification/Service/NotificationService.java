package com.example.springvelocity.Notification.Service;

import com.example.springvelocity.Notification.Entity.Notification;

public interface NotificationService {
    /**
     * add new notification to database
     * @param notification
     * @return notification added
     */
    Notification addNotification(Notification notification);

}
