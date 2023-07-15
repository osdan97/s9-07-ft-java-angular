package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.TransactionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@DiscriminatorValue("order")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Orders extends Transactions implements Serializable {

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "account_uuid", nullable = true)
    private Account account;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    @OneToOne(cascade = CascadeType.ALL)
    private Pay pay;

    @Column(name = "amount_taxes")
    private Double amountTaxes;

    @Column(name = "amount_total")
    private Double amountTotal;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name = "quantity", nullable = false, updatable = false)
    private Integer quantity;

    @Column(name="transaction_type",nullable = false)
    public String transaction_type;

    @OneToMany(targetEntity = OrderDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_uuid", referencedColumnName = "transaction_uuid")
    private List<OrderDetails> orderDetailsList;

    @OneToOne(mappedBy = "order")
    private ShippingDetails shippingDetails;

    public Orders(Double total){
        super(total);
    }
}
