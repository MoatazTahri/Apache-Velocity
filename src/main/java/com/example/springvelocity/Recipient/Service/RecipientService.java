package com.example.springvelocity.Recipient.Service;

import com.example.springvelocity.Notification.Exception.RecipientNotFoundException;
import com.example.springvelocity.Recipient.Entity.Recipient;

import java.util.List;

public interface RecipientService {
    Recipient getRecipientByEmail(String email) throws RecipientNotFoundException;
    List<Recipient> getAllRecipients();
    Recipient addRecipient(Recipient recipient);
}
