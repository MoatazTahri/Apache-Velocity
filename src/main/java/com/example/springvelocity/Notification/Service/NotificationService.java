package com.example.springvelocity.Notification.Service;

import com.example.springvelocity.Notification.Entity.Notification;

import java.util.List;

public interface NotificationService {
    /**
     * method for add new notification to DB
     * @param notification
     * @return entity saved
     */
    Notification addNotification(Notification notification);

    /**
     * get all notifications that have a specific recipient
     * @param address string
     * @return list of notifications
     */
    List<Notification> getNotificationsWhereRecipient(String address);
}
