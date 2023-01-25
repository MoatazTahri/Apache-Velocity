package com.leaf.NetworkMS.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "organization")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String isp;

    @OneToMany(mappedBy = "organization",fetch = FetchType.LAZY)
    private Set<NetworkEntity> networks;

    public OrganizationEntity(String name, String isp) {
        this.name = name;
        this.isp = isp;
    }
}
