package com.example.springvelocity;

import com.example.springvelocity.Email.Model.MessageTemplate;

import javax.mail.MessagingException;

public interface VelocityEmailService {

    void sendEmail(MessageTemplate message) throws MessagingException;
}
