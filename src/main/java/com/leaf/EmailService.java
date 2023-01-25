package com.leaf;

import com.leaf.EmailMS.Model.MessageTemplate;

public interface EmailService {
    void sendEmail(MessageTemplate message) throws Exception;
}
