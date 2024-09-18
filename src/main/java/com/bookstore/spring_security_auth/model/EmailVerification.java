package com.bookstore.spring_security_auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CT_EMAIL_VERIFICATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerification {
    @Id
    @GeneratedValue(generator = "UUID_GENERATOR")
    @GenericGenerator(name = "UUID_GENERATOR", strategy =
            "org.hibernate.id.UUIDGenerator")
    private String verificationId;
    private String username;


    public EmailVerification(String username) {
        this.username = username;
    }


}

