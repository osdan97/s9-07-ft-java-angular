package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.ProductState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "product_uuid")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "weight", nullable = false)
    private Double weight;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "min_stock", nullable = false)
    private Integer minStock;
    @Column(name = "state", nullable = false)
    private ProductState state;
    @ManyToOne
    @JoinColumn(name = "category_uuid")
    private Category category;
}
