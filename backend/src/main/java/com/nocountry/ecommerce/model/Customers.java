package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.Entities;
import com.nocountry.ecommerce.util.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@DiscriminatorValue("customer")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customers extends Account{

    @Column(name = "number")
    private String number;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "country")
    private String country;
    @OneToMany(targetEntity = Phones.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "number", referencedColumnName = "number")
    private List<Phones> phonesList;
    
    public Customers(String email, String password){
        super(email,password);
    }
}
