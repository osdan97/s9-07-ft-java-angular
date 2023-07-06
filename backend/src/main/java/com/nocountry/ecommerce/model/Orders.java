package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.TransactionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@DiscriminatorValue("order")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Orders extends Transactions{

    @Column(name = "number")
    private String number;
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Customers customers;
    @Column(name = "shipping_cost")
    private Double shippingCost;

    @Column(name = "amount_taxes")
    private Double amountTaxes;

    @Column(name = "amount_total")
    private Double amountTotal;

    @OneToMany(targetEntity = Phones.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "number", referencedColumnName = "number")
    private List<OrderDetails> orderDetailsList;
    @OneToMany(targetEntity = Phones.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "number", referencedColumnName = "number")
    private List<ShippingDetails> shippingDetailsList;

    public Orders(Double total){
        super(total);
    }
}
