package com.nocountry.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "lastsession_date", nullable = false)
    private LocalDateTime lastSessionDate;

    @Column(name = "active")
    private boolean active;


    public Account(String email, String password){
        this.accountUuid = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.createdDate = LocalDateTime.now();
        this.lastSessionDate = LocalDateTime.now();
        this.active = false;
    }
}
