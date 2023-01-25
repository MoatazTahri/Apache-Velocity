package com.leaf;

import com.leaf.EmailMS.Model.MessageTemplate;
import com.leaf.PropertiesConf.MessageProperties;
import com.leaf.PropertiesConf.VelocityTemplateProperties;
import com.leaf.Tools.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.JsonParseException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Component
@PropertySources({
        @PropertySource("classpath:properties/velocity/velocity.properties"),
        @PropertySource("classpath:properties/languages/${application.language}.messages.properties")
})
public class MailPreparator {

    @Autowired
    private VelocityTemplateProperties vtp;

    @Autowired
    private MessageProperties mp;

    @Autowired
    private VelocityEngine velocityEngine;

    public void prepareMail(MimeMessageHelper messageHelper, MessageTemplate message) throws Exception {
        MimeMessage mimeMessage = messageHelper.getMimeMessage();
        for (InternetAddress address : message.getRecipients()) {
            mimeMessage.addRecipient(message.getEmail().getMessageType().toRecipientType(), address);
        }
        messageHelper.setFrom(new InternetAddress(message.getFrom()));
        messageHelper.setSubject(message.getEmail().getSubject());
        messageHelper.setSentDate(new Date());
        velocityBuilder(message, messageHelper);

    }

    private void velocityBuilder(MessageTemplate message, MimeMessageHelper messageHelper) throws Exception {
        VelocityContext context = new VelocityContext();
        if (message.getEmail().getJsonParams() != null) {
            JSONObject params = new JSONObject(message.getEmail().getJsonParams());
            generateContext(context, params);
        }
        String template = message.getEmail().getTemplateName();
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(template, vtp.getEncoding(), context, stringWriter);
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
        String template = message.getEmail().getTemplateName();
        int messageId = message.getEmail().getId();
        Optional.of(Boolean.TRUE.equals(JsonUtils.isValidJSON(message.getEmail().getJsonParams()))).filter(bool -> bool)
                .orElseThrow(JsonParseException::new);
        Optional.of(Boolean.FALSE.equals(StringUtils.isEmpty(message.getEmail().getRecipients()))).filter(bool -> bool)
                .orElseThrow(() -> new MessagingException(String.format(mp.getException().getNoEmailAddressFound(), messageId)));
        Optional.of(Boolean.TRUE.equals(velocityEngine.resourceExists(template)) && StringUtils.isNotEmpty(template)).filter(bool -> bool)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(mp.getException().getFileNotFound() + " : " + template, messageId)));
    }
}
