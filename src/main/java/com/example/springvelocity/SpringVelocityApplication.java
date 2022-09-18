package com.example.springvelocity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties
@PropertySource(value = "classpath:languages/eng.messages.properties")
public class SpringVelocityApplication {
    @Value("${file-not-found.exception.message}")
    private String VELOCITY_TEMPLATE_FOLDER;

    @EventListener(ApplicationReadyEvent.class)
    public void a(){
        System.out.println(VELOCITY_TEMPLATE_FOLDER);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringVelocityApplication.class, args);
    }

}
