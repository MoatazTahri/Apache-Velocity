package com.leaf.NotificationMS.Repository;

import com.leaf.NotificationMS.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Integer> {

}
