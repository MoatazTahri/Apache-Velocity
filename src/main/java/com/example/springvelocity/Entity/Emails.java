package com.example.springvelocity.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Emails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String header;
    private String body;
    private String footer;
    private String mentions;

    public Emails(String header, String body, String footer, String mentions) {
        this.header = header;
        this.body = body;
        this.footer = footer;
        this.mentions = mentions;
    }
}
