package com.example.springvelocity.Entity;

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
    @OneToMany(mappedBy = "network",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Notification> notifications;
    @ManyToOne(targetEntity = Localization.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "localization_id",nullable = false)
    private Localization localization;
    private String publicIp;
    private String organization;

    public Network(Localization localization, String publicIp, String organization) {
        this.localization = localization;
        this.publicIp = publicIp;
        this.organization = organization;
    }
}
