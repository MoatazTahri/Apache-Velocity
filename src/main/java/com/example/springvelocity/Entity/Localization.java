package com.example.springvelocity.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "localization",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Network> networks;
    private String country;
    private String city;
    private String region;
    private String coordinates;
    private String timeZone;

    public Localization(String country, String city, String region, String coordinates, String timeZone) {
        this.country = country;
        this.city = city;
        this.region = region;
        this.coordinates = coordinates;
        this.timeZone = timeZone;
    }
}
