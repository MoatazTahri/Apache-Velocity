package com.example.springvelocity.Recipient.Entity;

import com.example.springvelocity.Notification.Entity.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "recipient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    public Recipient(String firstName, String lastName,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
    }
}
