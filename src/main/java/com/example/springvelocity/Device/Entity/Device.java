package com.example.springvelocity.Device.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // comment forcer fetch type LAZY, pour qu'il remplis les valeurs de son dependance.
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    private String localIp;

    private String macAddress;

    public Device(String localIp, String macAddress) {
        this.localIp = localIp;
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        // extérnaliser le msg sous forme
        // NotificationDevice, id : {0},  notifications : {1},  localIp : {2},  macAddress : {3},
        return "NotificationDevice{" +
                "id=" + id +
                ", notifications=" + notifications +
                ", localIp='" + localIp + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
