package com.leaf.Batch;

import com.leaf.EmailMS.Entity.EmailEntity;
import com.leaf.EmailMS.Model.MessageTemplate;
import com.leaf.EmailService;
import com.leaf.PropertiesConf.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@PropertySource("classpath:properties/languages/${application.language}.messages.properties")
public class EmailProcessor implements ItemProcessor<EmailEntity, EmailEntity> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageProperties messageProperties;

    @Override
    public EmailEntity process(EmailEntity email) {
        log.info(String.format(messageProperties.getInfo().getEmailTryingSend(), email.getId()));
        MessageTemplate message = new MessageTemplate("kyo.minia1@gmail.com", email);
        try {
            emailService.sendEmail(message);
            email.setSent(Boolean.TRUE);
        } catch (Exception e) {
            log.error(e.getClass().getName() + " : " + e.getMessage());
        }
        return email;
    }
}
