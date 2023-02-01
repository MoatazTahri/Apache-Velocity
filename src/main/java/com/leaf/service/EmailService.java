package com.leaf.service;

import com.leaf.emailMS.Model.MessageTemplate;

public interface EmailService {
    void sendEmail(MessageTemplate message) throws Exception;
}
