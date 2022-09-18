package com.example.springvelocity.Notification.Repository;

import com.example.springvelocity.Notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

}
