package com.leaf.NotificationMS.Service;

import com.leaf.NotificationMS.Entity.NotificationEntity;

public interface NotificationService {
    /**
     * add new notification to database
     *
     * @param notification
     * @return notification added
     */
    NotificationEntity addNotification(NotificationEntity notification);

}
