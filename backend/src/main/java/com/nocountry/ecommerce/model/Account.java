package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorColumn(name="entity", discriminatorType=DiscriminatorType.STRING)
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "account_uuid")
    private String accountUuid;

    @Column(name = "email", nullable = false,unique = true)
    @Email(message = "Please enter a valid email!")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role rol;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "lastsession_date", nullable = false)
    private LocalDateTime lastSessionDate;

    @Column(name = "active")
    private boolean active;
    @Column(name = "verification_code", updatable = false)
    private String verificationCode;
    @Transient
    private String token;

    public Account(String email, String password){
        this.accountUuid = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.createdDate = LocalDateTime.now();
        this.lastSessionDate = LocalDateTime.now();
        this.active = false;
    }
}
