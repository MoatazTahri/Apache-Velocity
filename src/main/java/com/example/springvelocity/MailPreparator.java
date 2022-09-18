package com.example.springvelocity;

import com.example.springvelocity.Email.Model.MessageTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;

@Component

public class MailPreparator {

    @Value("${velocity.resources.static-location}")
    private String VELOCITY_TEMPLATE_FOLDER;

    @Autowired
    private VelocityEngine velocityEngine;

    public void prepareMail(MimeMessageHelper messageHelper, MessageTemplate message) throws MessagingException, JSONException {
        MimeMessage mimeMessage = messageHelper.getMimeMessage();
        for (InternetAddress address : message.getRecipients()) {
            mimeMessage.addRecipient(message.getEmail().getMessageType().toRecipientType(), address);
        }
        messageHelper.setFrom(new InternetAddress(message.getFrom()));
        messageHelper.setSubject(message.getEmail().getSubject());

        messageHelper.setSentDate(new Date());
        velocityBuilder(message, messageHelper);

    }

    private void velocityBuilder(MessageTemplate message, MimeMessageHelper messageHelper) throws JSONException, MessagingException {
        VelocityContext context = new VelocityContext();
        if (message.getEmail().getJsonParams() != null) {
            JSONObject params = new JSONObject(message.getEmail().getJsonParams());
            generateContext(context, params);
        }
        String template = VELOCITY_TEMPLATE_FOLDER + message.getEmail().getTemplateName();
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(template, "UTF-8", context, stringWriter);
        messageHelper.setText(stringWriter.toString(), true);
    }

    private void generateContext(Context context, JSONObject parameters) throws JSONException {
        Iterator iterator = parameters.keys();
        while (iterator.hasNext()) {
            Object i = iterator.next();
            context.put(i.toString(), parameters.get(i.toString()));
        }
    }

    public void validate(MessageTemplate message) throws MessagingException {
        if (CollectionUtils.isEmpty(message.getRecipients())) {
            // extérnalisr les messages dans un fichier de configuration, pour qu'ils soient
            // eligible a la tradcution
            throw new MessagingException("Your email has no recipient");
        }
        // fr.messages.properties
        // ar.messages.properties
        String template = VELOCITY_TEMPLATE_FOLDER + message.getEmail().getTemplateName();
        if (!velocityEngine.resourceExists(template)) {
            throw new MessagingException("the template doesn't exist :" + template);
        }
    }
}
