package com.example.springvelocity.Entity;

import com.example.springvelocity.Enumeration.EmailConfigurationType;
import com.example.springvelocity.Enumeration.EmailType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String emailFrom;
    @Enumerated(EnumType.STRING)
    private EmailType emailType;
    @ManyToOne(targetEntity = Device.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id",nullable = false,referencedColumnName = "id")
    private Device device;
    @ManyToOne(targetEntity = Network.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "network_id",nullable = false,referencedColumnName = "id")
    private Network network;
    private Date sentDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "notification_id")
    private Set<Recipient> recipients;
    @Enumerated(EnumType.STRING)
    private EmailConfigurationType configurationType;

    public Notification(String emailFrom, EmailType emailType, Device device, Network network, Date sentDate,EmailConfigurationType configurationType) {
        this.emailFrom = emailFrom;
        this.emailType = emailType;
        this.device = device;
        this.network = network;
        this.sentDate = sentDate;
        this.configurationType=configurationType;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailType=" + emailType +
                ", device=" + device +
                ", recipients=" + recipients +
                ", network=" + network +
                ", sentDate=" + sentDate +
                ", configurationType=" + configurationType +
                '}';
    }
}
