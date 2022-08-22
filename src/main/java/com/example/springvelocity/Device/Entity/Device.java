package com.example.springvelocity.Device.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "device",fetch = FetchType.LAZY)
    private Set<Notification> notifications;
    private String localIp;
    private String macAddress;

    public Device(String localIp, String macAddress) {
        this.localIp = localIp;
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "NotificationDevice{" +
                "id=" + id +
                ", notifications=" + notifications +
                ", localIp='" + localIp + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
