package com.example.springvelocity.Email.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import com.example.springvelocity.Notification.Enumeration.EmailType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email")
@Getter
@Setter
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String templateName;
    @Column(columnDefinition = "TEXT")
    private String jsonParams;
    @ElementCollection
    private Set<String> attachments;
    private String subject;
    @Enumerated(EnumType.STRING)
    private EmailType emailType;
    @OneToMany(mappedBy = "email",fetch = FetchType.LAZY)
    private Set<Notification> notifications;
    @Column(columnDefinition = "boolean default false")
    private boolean sent;

    public Email(String templateName, String jsonParams, Set<String> attachments, String subject, EmailType emailType, boolean sent) {
        this.templateName = templateName;
        this.jsonParams = jsonParams;
        this.attachments = attachments;
        this.subject = subject;
        this.emailType = emailType;
        this.sent = sent;
    }
}
