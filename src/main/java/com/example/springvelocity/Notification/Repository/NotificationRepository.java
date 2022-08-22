package com.example.springvelocity.Notification.Repository;

import com.example.springvelocity.Notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query(value = "select * from notification n inner join notification_recipient nr on n.id=nr.id_notification where nr.email_to=?1",nativeQuery = true)
    List<Notification> findAllByRecipientAddress(String address);
}
