package com.example.springvelocity.Semaine1.Service.Email;

import com.example.springvelocity.Semaine1.Entity.User;
import freemarker.ext.beans.MapModel;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service("velocity")
public class VelocityEmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Override
    public void sendEmailWithAttachment(final String from,
                                        final String to,
                                        final String body,
                                        final String subject,
                                        final String attachment,
                                        final User user) throws MessagingException {
        MimeMessagePreparator preparator= mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
            messageHelper.setTo(to);
            messageHelper.setFrom(new InternetAddress(from));
            messageHelper.setSubject(subject);
            messageHelper.setSentDate(new Date());
            FileSystemResource resource=new FileSystemResource(attachment);
            messageHelper.addAttachment(resource.getFilename(),resource);
            VelocityContext context=new VelocityContext();
            context.put("user",user);
            context.put("recipientName",to.split("\\.")[0]);
            SimpleDateFormat sdf=new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
            context.put("date",sdf.format(mimeMessage.getSentDate()));
            StringWriter stringWriter=new StringWriter();
            velocityEngine.mergeTemplate("src/main/webapp/WEB-INF/pages/Home.vm","UTF-8",context,stringWriter);
            messageHelper.setText(stringWriter.toString(),true);

        };
        mailSender.send(preparator);
        System.out.println("email sent");

    }

    @Override
    public void sendSimpleEmail(String from, String to, String body, String subject) {

    }
}
