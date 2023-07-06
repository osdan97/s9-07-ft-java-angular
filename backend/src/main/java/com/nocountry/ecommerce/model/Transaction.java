package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @Column(name = "transaction_uuid", nullable = false,unique = true)
    private String id;
    @Column(name="name_operation", nullable = false)
    private String name_operation;
    @Column(name="cant", nullable = false)
    private int cant;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "inventory_uuid")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
}
