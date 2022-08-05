package com.example.springvelocity.Service.Notification;

import com.example.springvelocity.Repository.NotificationRecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationRecipientImpl implements NotificationRecipientService{
    @Autowired
    private NotificationRecipientRepository nrRepository;
}
