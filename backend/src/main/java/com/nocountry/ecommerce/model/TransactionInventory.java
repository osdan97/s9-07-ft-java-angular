package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@DiscriminatorValue("inventory")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TransactionInventory extends Transactions {

    @Column(name = "number")
    private String number;

    @Column(name="transaction_type",nullable = false)
    public String transaction_type;

    @Column(name = "quantity", nullable = false, updatable = false)
    private Integer quantity;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="inventory_uuid",nullable = false)
    public Inventory inventory;

public TransactionInventory (Double total){
    super(total);

}




}
