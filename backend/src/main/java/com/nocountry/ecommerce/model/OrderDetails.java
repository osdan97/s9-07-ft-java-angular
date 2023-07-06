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
@Table(name = "ORDERDETAILS")
public class OrderDetails implements Serializable {

    @Id
    @Column(name = "orderdetail_uuid")
    private String orderDetailUuid;
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "taxes")
    private Double taxes;
    @Column(name = "total")
    private Double total;
}
