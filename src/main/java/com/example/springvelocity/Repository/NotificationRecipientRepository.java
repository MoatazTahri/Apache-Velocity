package com.example.springvelocity.Repository;

import com.example.springvelocity.Entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRecipientRepository extends JpaRepository<Recipient,Integer> {
}
