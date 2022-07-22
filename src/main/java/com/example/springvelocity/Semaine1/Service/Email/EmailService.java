package com.example.springvelocity.Semaine1.Service.Email;

import com.example.springvelocity.Semaine1.Entity.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmailWithAttachment(String from, String to, String body, String subject, String attachment, User user) throws MessagingException;
    void sendSimpleEmail(String from,String to,String body,String subject);
}
