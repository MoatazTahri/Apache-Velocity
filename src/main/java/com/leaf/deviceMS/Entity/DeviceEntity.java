package com.leaf.deviceMS.Entity;

import com.leaf.notificationMS.Entity.NotificationEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "device")
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    private Set<NotificationEntity> notifications;

    private String localIp;

    private String macAddress;

    private String processor;

    private String manufacturer;

    private String model;

    private String operatingSystem;
}
