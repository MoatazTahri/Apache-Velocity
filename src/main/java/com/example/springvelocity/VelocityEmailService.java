package com.example.springvelocity;

import com.example.springvelocity.Email.Model.AutomaticMessageTemplate;
import com.example.springvelocity.Email.Model.ManualMessageTemplate;

import javax.mail.MessagingException;

public interface VelocityEmailService {

    /**
     this is a void method for sending emails automatic
     @param message create it by MessageTemplate
     **/
    void sendEmailAutomatic(AutomaticMessageTemplate message) throws MessagingException;

    /**
     this is a void method for sending emails manual
     @param message create it by MessageTemplate
     **/
    void sendEmailManual(ManualMessageTemplate message) throws MessagingException;
}
