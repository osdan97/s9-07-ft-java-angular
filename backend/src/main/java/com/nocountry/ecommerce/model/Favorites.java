package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name= "FAVORITES")
public class Favorites implements Serializable {
    @Id
    @Column (name = "favorites_uuid")
    private String id;
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Customers customers;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    }

