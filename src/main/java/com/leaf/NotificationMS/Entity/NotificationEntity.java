package com.leaf.NotificationMS.Entity;

import com.leaf.DeviceMS.Entity.DeviceEntity;
import com.leaf.EmailMS.Entity.EmailEntity;
import com.leaf.LocalizationMS.Entity.CityEntity;
import com.leaf.NetworkMS.Entity.NetworkEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emailFrom;

    @ManyToOne(targetEntity = DeviceEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @ManyToOne(targetEntity = NetworkEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id", nullable = false, referencedColumnName = "id")
    private NetworkEntity network;

    private Date sentDate;

    @ManyToOne(targetEntity = CityEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    private EmailEntity email;

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", emailFrom='" + emailFrom + '\'' +
                ", device=" + device +
                ", network=" + network +
                ", sentDate=" + sentDate +
                ", city=" + city +
                '}';
    }
}
