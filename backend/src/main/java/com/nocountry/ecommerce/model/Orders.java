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
    @JoinColumn(name = "customer_uuid")
    private Customers customers;
    @Column(name = "shipping_cost")
    private Double shippingCost;

    @Column(name = "amount_taxes")
    private Double amountTaxes;

    @Column(name = "amount_total")
    private Double amountTotal;

    @OneToMany(targetEntity = OrderDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_uuid", referencedColumnName = "transaction_uuid")
    private List<OrderDetails> orderDetailsList;
    @OneToMany(targetEntity = ShippingDetails.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_uuid", referencedColumnName = "transaction_uuid")
    private List<ShippingDetails> shippingDetailsList;

    public Orders(Double total){
        super(total);
    }
}
