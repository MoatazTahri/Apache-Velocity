package com.leaf.localizationMS.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "country")
public class CountryEntity {

    @Id
    @Column(name = "code")
    private String code;

    private String name;

    private String phone;

    @ManyToOne(targetEntity = ContinentEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "continentId")
    private ContinentEntity continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CityEntity> cities;

}
