package com.example.springvelocity.Network.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String isp;
    @OneToMany(mappedBy = "organization",fetch = FetchType.LAZY)
    private Set<Network> networks;

    public Organization(String name, String isp) {
        this.name = name;
        this.isp = isp;
    }
}
