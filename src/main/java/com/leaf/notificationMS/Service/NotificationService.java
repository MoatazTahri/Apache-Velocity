package com.leaf.notificationMS.Service;

import com.leaf.notificationMS.Entity.NotificationEntity;

public interface NotificationService {
    /**
     * add new notification to database
     * @param notification {@link NotificationEntity}
     * @return the added notification
     */
    NotificationEntity addNotification(NotificationEntity notification);

}
