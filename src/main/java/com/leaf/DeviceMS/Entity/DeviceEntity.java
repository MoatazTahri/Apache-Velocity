package com.leaf.DeviceMS.Entity;

import com.leaf.NotificationMS.Entity.NotificationEntity;
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
}
