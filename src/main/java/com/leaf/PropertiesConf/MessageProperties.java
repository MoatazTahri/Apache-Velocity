package com.leaf.PropertiesConf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "message")
public class MessageProperties {

    private final MessageProperties.Exception exception = new Exception();

    private final MessageProperties.Info info = new Info();

    public Exception getException() {
        return exception;
    }

    public Info getInfo() {
        return info;
    }

    public static class Exception {

        private String fileNotFound = "The Velocity template does not exist";

        private String noEmailAddressFound = "Your email has no recipient";

        private String invalidAddressEmail = "Unable to convert email address";

        public String getFileNotFound() {
            return fileNotFound;
        }

        public void setFileNotFound(String fileNotFound) {
            this.fileNotFound = fileNotFound;
        }

        public String getNoEmailAddressFound() {
            return noEmailAddressFound;
        }

        public void setNoEmailAddressFound(String noEmailAddressFound) {
            this.noEmailAddressFound = noEmailAddressFound;
        }

        public String getInvalidAddressEmail() {
            return invalidAddressEmail;
        }

        public void setInvalidAddressEmail(String invalidAddressEmail) {
            this.invalidAddressEmail = invalidAddressEmail;
        }
    }

    public static class Info {

        String EmailTryingSend = "Trying to send email %d. Please wait...";

        String EmailIsSending = "Message %d is sending to emails %s";

        String EmailIsSent = "Email %d is sent successfully";

        public String getEmailTryingSend() {
            return EmailTryingSend;
        }

        public void setEmailTryingSend(String emailSendingTry) {
            EmailTryingSend = emailSendingTry;
        }

        public String getEmailIsSending() {
            return EmailIsSending;
        }

        public void setEmailIsSending(String emailIsSending) {
            EmailIsSending = emailIsSending;
        }

        public String getEmailIsSent() {
            return EmailIsSent;
        }

        public void setEmailIsSent(String emailIsSent) {
            EmailIsSent = emailIsSent;
        }
    }
}
