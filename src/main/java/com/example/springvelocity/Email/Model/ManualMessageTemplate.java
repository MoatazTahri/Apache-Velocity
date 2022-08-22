package com.example.springvelocity.Email.Model;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Notification.Enumeration.EmailType;
import com.example.springvelocity.Recipient.Entity.Recipient;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

@Getter
public class ManualMessageTemplate {
    private String from;
    private String recipientEmailAddress;
    private Email email;

    public ManualMessageTemplate(String from, String recipientEmailAddress, Email email) {
        this.from = from;
        this.recipientEmailAddress = recipientEmailAddress;
        this.email = email;
    }
}
