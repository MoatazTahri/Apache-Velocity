package com.leaf;

import com.leaf.EmailMS.Model.MessageTemplate;
import com.leaf.NotificationMS.Entity.NotificationEntity;
import com.leaf.NotificationMS.Service.NotificationService;
import com.leaf.PropertiesConf.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

        // Email validation
        mailPreparator.validate(message);
        log.info(String.format(messageProperties.getInfo().getEmailIsSending(), message.getEmail().getId(), message.getRecipients()));

        // Email and template initialization
        mailPreparator.prepareMail(messageHelper, message);

        // Build notification
        NotificationEntity notification = notificationBuilder.build(mimeMessage, message);

        // send email
        mailSender.send(mimeMessage);

        // save notification
        notificationService.addNotification(notification);
        log.info(String.format(messageProperties.getInfo().getEmailIsSent(), message.getEmail().getId()));
    }
}
