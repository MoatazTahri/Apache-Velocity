package com.example.springvelocity.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "device",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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
