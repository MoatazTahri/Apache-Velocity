package com.example.springvelocity.Localization.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    private String id;

    private String name;

    private String longitude;

    private String latitude;

    @ManyToOne(targetEntity = Country.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_code")
    private Country country;

    private String country_code3;

    private int population;

    @OneToMany(mappedBy = "city")
    private Set<Notification> notifications;
}
