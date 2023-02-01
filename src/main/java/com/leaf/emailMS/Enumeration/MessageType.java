package com.leaf.emailMS.Enumeration;

import javax.mail.Message;

public enum MessageType {
    /**
     * Carbon copy
     */
    CC,

    /**
     * Blind carbon copy
     */
    BCC;

    public Message.RecipientType toRecipientType() {
        if (this.equals(MessageType.CC))
            return Message.RecipientType.CC;
        return Message.RecipientType.BCC;
    }
}
