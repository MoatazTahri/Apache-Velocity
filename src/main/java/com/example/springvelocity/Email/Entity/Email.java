package com.example.springvelocity.Email.Entity;

import com.example.springvelocity.Email.Enumeration.MessageType;
import com.example.springvelocity.Notification.Entity.Notification;
import com.example.springvelocity.Email.Enumeration.EmailType;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Entity
@Builder
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

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String recipients;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    @OneToMany(mappedBy = "email", fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    @Column(columnDefinition = "boolean default false")
    private boolean sent;
}
