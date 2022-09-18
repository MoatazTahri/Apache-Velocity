package com.example.springvelocity;

import com.example.springvelocity.Notification.Entity.Notification;
import com.example.springvelocity.Email.Model.MessageTemplate;
import com.example.springvelocity.Notification.Service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VelocityEmailServiceImpl implements VelocityEmailService {

    private JavaMailSender mailSender;

    private NotificationBuilder notificationBuilder;

    private MailPreparator mailPreparator;

    private NotificationService notificationService;

    @Override
    public void sendEmail(MessageTemplate message) {
        MimeMessagePreparator preparator = mimeMessage -> {
            System.out.printf("Message %d is sending to emails " + message.getRecipients() + "\n", message.getEmail().getId());
            //
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            // Validation du contenu d'email
            mailPreparator.validate(message);

            // Initialisation d'Email et de Template
            mailPreparator.prepareMail(messageHelper, message);

            // decorator
            // strategy

            // Les données géographiques de l'envoyeur d'Email
            Notification notification = notificationBuilder.build(mimeMessage, message);


            notificationService.addNotification(notification);
        };
        mailSender.send(preparator);
        System.out.println("email sent");
    }






}
