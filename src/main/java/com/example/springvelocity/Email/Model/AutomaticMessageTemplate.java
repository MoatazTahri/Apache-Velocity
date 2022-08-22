package com.example.springvelocity.Email.Model;

import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Recipient.Entity.Recipient;
import lombok.Getter;

import java.util.List;

@Getter
public class AutomaticMessageTemplate {
    private String from;
    private String recipientEmailAddress;
    private String template;
    private Email email;

    public AutomaticMessageTemplate(String from, String recipientEmailAddress, Email email) {
        this.from = from;
        this.recipientEmailAddress = recipientEmailAddress;
        this.email = email;
        this.template="src/main/webapp/WEB-INF/pages/mail.vm";
    }
}
