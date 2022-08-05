package com.example.springvelocity.Model;

import com.example.springvelocity.Enumeration.EmailType;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

@Getter
public class MessageTemplate {
    private String from;
    private List<String> to;
    private String subject;
    private List<String> attachments;
    private String template;
    private JSONObject params;
    private MessageTemplateConfiguration configuration;
    private EmailType emailType;

    public MessageTemplate(String from, List<String> to, String subject, List<String> attachments, MessageTemplateConfiguration configuration,EmailType emailType) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.attachments = attachments;
        this.configuration = configuration;
        this.template="src/main/webapp/WEB-INF/pages/mail.vm";
        this.emailType=emailType;
    }

    public MessageTemplate(String from, List<String> to, String subject, List<String> attachments, String template, JSONObject params,EmailType emailType) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.attachments = attachments;
        this.template = template;
        this.params = params;
        this.emailType=emailType;
    }
}
