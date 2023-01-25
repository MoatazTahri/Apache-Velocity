package com.leaf;

import com.leaf.EmailMS.Entity.EmailEntity;
import com.leaf.EmailMS.Enumeration.EmailType;
import com.leaf.EmailMS.Enumeration.MessageType;
import com.leaf.EmailMS.Model.MessageTemplate;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class SpringVelocityApplicationTests {

    @Autowired
    private MailPreparator mailPreparator;

    @Autowired
    private JavaMailSender mailSender;

    private MimeMessageHelper messageHelper;

    @BeforeEach
    void init() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("template_not_exist_exception")
    @Test
    void should_return_template_existence_exception() {
        EmailEntity email = EmailEntity.builder()
                .templateName("")
                .jsonParams("{}")
                .subject("test")
                .recipients("kyo.minia1@gmail.com")
                .messageType(MessageType.BCC)
                .emailType(EmailType.NOTIFICATION)
                .build();
        MessageTemplate messageTemplate = new MessageTemplate("kyo.minia1@gmail.com", email);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> mailPreparator.validate(messageTemplate));
    }

    @DisplayName("recipient_not_found_exception")
    @Test
    void should_return_recipient_exception() {
        EmailEntity email = EmailEntity.builder()
                .templateName("src/main/resources/email.vm")
                .jsonParams("{}")
                .subject("test")
                .recipients("")
                .messageType(MessageType.BCC)
                .emailType(EmailType.NOTIFICATION)
                .build();
        MessageTemplate messageTemplate = new MessageTemplate("kyo.minia1@gmail.com", email);
        Assertions.assertThrows(MessagingException.class, () -> mailPreparator.validate(messageTemplate));
    }

    @DisplayName("invalid_address_throws_nothing")
    @Test
    void invalid_address_is_not_error() {
        EmailEntity email = EmailEntity.builder()
                .templateName("src/main/resources/email.vm")
                .jsonParams("{}")
                .subject("test")
                .recipients("haha@")
                .messageType(MessageType.BCC)
                .emailType(EmailType.NOTIFICATION)
                .build();
        MessageTemplate messageTemplate = new MessageTemplate("kyo.minia1@gmail.com", email);
        Assertions.assertDoesNotThrow(() -> mailPreparator.prepareMail(messageHelper, messageTemplate));
    }

    @DisplayName("invalid_json_exception")
    @Test
    void should_return_json_exception() {
        EmailEntity email = EmailEntity.builder()
                .templateName("src/main/resources/email.vm")
                .jsonParams("{")
                .subject("test")
                .recipients("kyo.minia1@gmail.com")
                .messageType(MessageType.BCC)
                .emailType(EmailType.NOTIFICATION)
                .build();
        MessageTemplate messageTemplate = new MessageTemplate("kyo.minia1@gmail.com", email);
        Assertions.assertThrows(JsonParseException.class, () -> mailPreparator.validate(messageTemplate));
    }

    @DisplayName("null_message_type_throws_nothing")
    @Test
    void null_message_type_is_not_error() {
        EmailEntity email = EmailEntity.builder()
                .templateName("src/main/resources/email.vm")
                .jsonParams("{}")
                .subject("test")
                .recipients("kyo.minia1@gmail.com")
                .messageType(null)
                .emailType(EmailType.NOTIFICATION)
                .build();
        MessageTemplate messageTemplate = new MessageTemplate("kyo.minia1@gmail.com", email);
        Assertions.assertDoesNotThrow(() -> mailPreparator.prepareMail(messageHelper, messageTemplate));
    }

}
