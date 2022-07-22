package com.example.springvelocity.Semaine1.Service.Email;

import com.example.springvelocity.Semaine1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("java")
public class JavaEmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmailWithAttachment(String from, String to, String body, String subject, String attachment, User user) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setText(body);
        messageHelper.setSubject(subject);

        FileSystemResource resource=new FileSystemResource(attachment);
        messageHelper.addAttachment(resource.getFilename(),resource);
        mailSender.send(message);
        System.out.println("mail sent");
    }

    @Override
    public void sendSimpleEmail(String from,String to, String body, String subject) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("mail sent");

    }
}
