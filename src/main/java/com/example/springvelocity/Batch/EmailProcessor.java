package com.example.springvelocity.Batch;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Email.Model.ManualMessageTemplate;
import com.example.springvelocity.Recipient.Entity.Recipient;
import com.example.springvelocity.Recipient.Service.RecipientService;
import com.example.springvelocity.VelocityEmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

@Slf4j
public class EmailProcessor implements ItemProcessor<Email,Email> {
    @Autowired
    private VelocityEmailService emailService;
    @Autowired
    private RecipientService recipientService;

    @Override
    public Email process(Email email) throws Exception {
        List<Recipient> recipients=recipientService.getAllRecipients();
        for (Recipient recipient:recipients){
            String recipientEmailAddress=recipient.getEmail();
            System.out.println("email "+email.getId()+" is sending to "+recipientEmailAddress);
            ManualMessageTemplate message=new ManualMessageTemplate("kyo.minia1@gmail.com",recipientEmailAddress,email);
            emailService.sendEmailManual(message);
        }
        if(CollectionUtils.isNotEmpty(recipients))
            email.setSent(true);
        return email;
    }
}
