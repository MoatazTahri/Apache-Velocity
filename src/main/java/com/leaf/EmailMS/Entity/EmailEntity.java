package com.leaf.EmailMS.Entity;

import com.leaf.EmailMS.Enumeration.EmailType;
import com.leaf.EmailMS.Enumeration.MessageType;
import com.leaf.NotificationMS.Entity.NotificationEntity;
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
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String templateName;

    @Column(columnDefinition = "LONGTEXT")
    private String jsonParams;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String recipients;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    @OneToMany(mappedBy = "email", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notifications;

    @Column(columnDefinition = "boolean default false")
    private boolean sent;

    public static class EmailEntityBuilder {

        public EmailEntityBuilder messageType(MessageType messageType) {
            this.messageType = messageType;
            if (messageType == null) {
                this.messageType = MessageType.BCC;
            }
            return this;
        }
    }
}
