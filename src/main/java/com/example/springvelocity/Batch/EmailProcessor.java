package com.example.springvelocity.Batch;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Model.MessageTemplate;
import com.example.springvelocity.VelocityEmailService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EmailProcessor implements ItemProcessor<Email, Email> {

    @Autowired
    private VelocityEmailService emailService;

    @Override
    public Email process(Email email) throws Exception {
        System.out.println("email " + email.getId() + " is sending");
        MessageTemplate message = new MessageTemplate("kyo.minia1@gmail.com", email);
        emailService.sendEmail(message);
        email.setSent(Boolean.TRUE);

        return email;
    }
}
