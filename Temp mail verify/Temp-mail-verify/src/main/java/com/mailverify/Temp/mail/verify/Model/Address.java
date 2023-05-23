package com.mailverify.Temp.mail.verify.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="email_address")
    private String emailAddress;
    private Boolean disposable;
    private Boolean spam;

    public Address(String emailAddress, Boolean disposable, Boolean spam) {
        this.emailAddress = emailAddress;
        this.disposable = disposable;
        this.spam = spam;
    }
}
