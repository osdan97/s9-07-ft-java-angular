package com.nocountry.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@DiscriminatorValue("user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Users extends Account implements Serializable {
    @Column(name = "number")
    private String number;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    public Users(String email, String password){
        super(email,password);
    }
    public Users(String email){
        super(email);
    }
}
