package com.leaf.emailMS.Model;

import com.leaf.emailMS.Entity.EmailEntity;
import com.leaf.propertiesConf.MessageProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
@PropertySource("classpath:properties/languages/${application.language}.messages.properties")
public class MessageTemplate {
    private final String from;

    private final EmailEntity email;

    @Autowired
    private MessageProperties messageProperties;

    private static final String EMAIL_SEPARATOR = ",";

    public MessageTemplate(String from, EmailEntity email) {
        this.from = from;
        this.email = email;
    }

    public List<InternetAddress> getRecipients() {
        EmailValidator validator = EmailValidator.getInstance();
        List<InternetAddress> addresses = new ArrayList<>();
        List<String> emailAddresses = Arrays.stream(email.getRecipients().split(EMAIL_SEPARATOR)).collect(Collectors.toList());
        emailAddresses.forEach(address -> {
            try {
                if (validator.isValid(address))
                    addresses.add(new InternetAddress(address));
            } catch (AddressException e) {
                log.error(messageProperties.getException().getInvalidAddressEmail());
            }
        });
        return addresses;
    }
}
