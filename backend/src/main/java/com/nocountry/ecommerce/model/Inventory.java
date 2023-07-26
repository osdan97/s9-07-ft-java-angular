package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="INVENTORY")
public class Inventory implements Serializable {
    @Id
    @Column(name = "inventory_uuid", nullable = false,unique = true)
    private String id;
    @Column(name = "name", nullable = false,unique = true)
    private String name;
    @Column(name = "purchase_price", nullable = false)
    private Double purchase_price;
    @Column(name = "selling_price", nullable = false)
    private Double selling_price;
    @Column(name = "image", nullable = false,unique = false)
    private String image;
    @Column(name = "stock_inventory", nullable = false)
    private int stock_inventory;
    @Column(name = "update_date", nullable = false, updatable = false)
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name="product_uuid",nullable = false)
    private Product product ;

}
