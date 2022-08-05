package com.example.springvelocity.Service.Email;

import com.example.springvelocity.Model.MessageTemplate;
import com.example.springvelocity.Entity.User;

import javax.mail.MessagingException;

public interface EmailService {
    /**
     this is a void method for sending emails
     @param message create it by MessageTemplate
     **/
    void sendEmail(MessageTemplate message) throws MessagingException;
}
