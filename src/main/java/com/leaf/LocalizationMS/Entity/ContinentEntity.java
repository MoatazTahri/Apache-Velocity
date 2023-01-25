package com.leaf.LocalizationMS.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "continent")
public class ContinentEntity {

    @Id
    @Column(name = "continentId")
    private String id;

    private String code;

    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CountryEntity> countries;
}
