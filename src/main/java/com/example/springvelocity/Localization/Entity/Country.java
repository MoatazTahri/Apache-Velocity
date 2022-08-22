package com.example.springvelocity.Localization.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "code")
    private String code;

    private String name;

    private String phone;

    @ManyToOne(targetEntity = Continent.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "continentId")
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<City> cities;

}
