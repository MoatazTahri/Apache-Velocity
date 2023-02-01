package com.leaf.notificationMS.Repository;

import com.leaf.notificationMS.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Integer> {

}
