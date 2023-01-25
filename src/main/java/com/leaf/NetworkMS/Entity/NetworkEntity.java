package com.leaf.NetworkMS.Entity;

import com.leaf.NotificationMS.Entity.NotificationEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "network")
public class NetworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "network", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notifications;

    private String publicIp;

    @ManyToOne(targetEntity = OrganizationEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;
}
