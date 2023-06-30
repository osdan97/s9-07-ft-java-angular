package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customers extends Account{
    @Column(name = "number")
    private String number;
    @Column(name = "entidad")
    private Entities entity;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;
    @OneToMany(targetEntity = Phones.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "number", referencedColumnName = "number", nullable = false)
    private List<Phones> phonesList;
}
