package com.example.springvelocity;

import com.example.springvelocity.Semaine1.Entity.User;
import com.example.springvelocity.Semaine1.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringVelocityApplication {
    @Autowired
    @Qualifier("velocity")
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringVelocityApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void trigger(){
        try {
            emailService.sendEmailWithAttachment("kyo.minia1@gmail.com",
                    "kyo.minia3@gmail.com",
                    "Hello kyo",
                    "Test",
                    "C:\\Users\\kyo_m\\Desktop\\spiderman-miles-lost-in-space-4k-0f.jpg",
                    new User("moataz","tahri","kyo.minia1@gmail.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
