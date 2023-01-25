package com.leaf.LocalizationMS.Entity;

import com.leaf.NotificationMS.Entity.NotificationEntity;
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
public class CityEntity {

    @Id
    @Column(name = "city_id")
    private String id;

    private String name;

    private String longitude;

    private String latitude;

    @ManyToOne(targetEntity = CountryEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_code")
    private CountryEntity country;

    private String country_code3;

    private int population;

    @OneToMany(mappedBy = "city")
    private Set<NotificationEntity> notifications;
}
