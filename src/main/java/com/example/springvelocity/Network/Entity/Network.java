package com.example.springvelocity.Network.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "network",fetch = FetchType.LAZY)
    private Set<Notification> notifications;
    private String publicIp;
    @ManyToOne(targetEntity = Organization.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

    public Network(String publicIp, Organization organization) {
        this.publicIp = publicIp;
        this.organization = organization;
    }
}
