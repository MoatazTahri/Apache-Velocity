package com.example.springvelocity.Notification.Entity;

import com.example.springvelocity.Device.Entity.Device;
import com.example.springvelocity.Email.Entity.Email;
import com.example.springvelocity.Localization.Entity.City;
import com.example.springvelocity.Recipient.Entity.Recipient;
import com.example.springvelocity.Notification.Enumeration.EmailConfigurationType;
import com.example.springvelocity.Notification.Enumeration.EmailType;
import com.example.springvelocity.Network.Entity.Network;
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
    @ManyToOne(targetEntity = Device.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;
    @ManyToOne(targetEntity = Network.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id",nullable = false,referencedColumnName = "id")
    private Network network;
    private Date sentDate;
    @ManyToOne(targetEntity = Recipient.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id",nullable = false)
    private Recipient recipient;
    @ManyToOne(targetEntity = City.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    private Email email;

    public Notification(String emailFrom, Device device, Network network, Date sentDate, Recipient recipient, City city, Email email) {
        this.emailFrom = emailFrom;
        this.device = device;
        this.network = network;
        this.sentDate = sentDate;
        this.recipient = recipient;
        this.city = city;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", emailFrom='" + emailFrom + '\'' +
                ", device=" + device +
                ", network=" + network +
                ", sentDate=" + sentDate +
                ", city=" + city +
                '}';
    }
}
