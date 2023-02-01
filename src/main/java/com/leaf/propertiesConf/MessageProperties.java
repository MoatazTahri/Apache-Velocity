package com.leaf.propertiesConf;

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

        private String noEmailAddressFound = "Your email has no recipient or all addresses are wrong";

        private String invalidAddressEmail = "Unable to convert email address";

        private String emailSendingError = "An error occurred while sending the email";

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

        public String getEmailSendingError() {
            return emailSendingError;
        }

        public void setEmailSendingError(String emailSendingError) {
            this.emailSendingError = emailSendingError;
        }
    }

    public static class Info {

        private String EmailTryingSend = "Trying to send email %d. Please wait...";

        private String EmailIsSending = "Message %d is sending to emails %s";

        private String EmailIsSent = "Email %d is sent successfully";

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
