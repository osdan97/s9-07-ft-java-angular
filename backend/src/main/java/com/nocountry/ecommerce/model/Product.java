package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.ProductState;
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
@Table(name = "PRODUCT")
public class Product implements Serializable {
    @Id
    @Column(name = "product_uuid",nullable = false,unique = true)
    private String id;
    @Column(name = "name", nullable = false,unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "weight", nullable = false)
    private Double weight;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "min_stock", nullable = true)
    private Integer minStock;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ProductState state;
    @ManyToOne
    @JoinColumn(name = "category_uuid", nullable = false)
    private Category category;
}
