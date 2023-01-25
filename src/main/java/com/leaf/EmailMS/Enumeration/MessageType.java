package com.leaf.EmailMS.Enumeration;

import javax.mail.Message;

public enum MessageType {
    CC,
    BCC;

    public Message.RecipientType toRecipientType() {
        if (this.equals(MessageType.CC))
            return Message.RecipientType.CC;
        return Message.RecipientType.BCC;
    }
}
