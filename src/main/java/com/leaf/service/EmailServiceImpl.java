package com.leaf.service;

import com.leaf.emailMS.Model.MessageTemplate;
import com.leaf.notificationMS.Entity.NotificationEntity;
import com.leaf.notificationMS.Service.NotificationService;
import com.leaf.propertiesConf.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@PropertySource("classpath:properties/languages/${application.language}.messages.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationBuilder notificationBuilder;

    @Autowired
    private MailPreparator mailPreparator;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public void sendEmail(MessageTemplate message) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        mailPreparator.validate(message);
        log.info(String.format(messageProperties.getInfo().getEmailIsSending(), message.getEmail().getId(), message.getRecipients()));
        mailPreparator.prepareMail(messageHelper, message);
        NotificationEntity notification = notificationBuilder.build(mimeMessage, message);
        try {
            mailSender.send(mimeMessage);
        } catch (MailException e){
            log.error(messageProperties.getException().getEmailSendingError());
        }
        notificationService.addNotification(notification);
        log.info(String.format(messageProperties.getInfo().getEmailIsSent(), message.getEmail().getId()));
    }
}
