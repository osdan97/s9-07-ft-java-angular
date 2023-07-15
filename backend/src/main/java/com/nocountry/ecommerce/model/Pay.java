package com.nocountry.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@DiscriminatorValue("pay")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Pay extends Transactions implements Serializable{
    @Column(name = "number")
    private String number;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "card_code")
    private String cardCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_uuid")
    private Orders orders;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name="transaction_type",nullable = false)
    public String transaction_type;
}
