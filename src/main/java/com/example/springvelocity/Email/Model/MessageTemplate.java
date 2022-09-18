package com.example.springvelocity.Email.Model;

import com.example.springvelocity.Email.Entity.Email;
import lombok.Getter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MessageTemplate {
    private final String from;
    private final Email email;

    public MessageTemplate(String from, Email email) {
        this.from = from;
        this.email = email;
    }

    public List<InternetAddress> getRecipients() {
        List<InternetAddress> addresses = Arrays.stream(email.getRecipients().split(",")).map(recipient -> {
            InternetAddress address = null;
            try {
                address = new InternetAddress(recipient);
            } catch (AddressException e) {
                e.printStackTrace();
            }
            return address;
        }).collect(Collectors.toList());
        return addresses;
    }
}
