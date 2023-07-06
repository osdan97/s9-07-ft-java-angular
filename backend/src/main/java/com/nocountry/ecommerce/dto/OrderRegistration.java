package com.nocountry.ecommerce.dto;

import com.nocountry.ecommerce.util.enums.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRegistration {
    private String number;
    private String fullName;
    private Double shippingCost;
    private Double amountTaxes;
    private Double amountTotal;
    private Double total;
    private LocalDateTime createdDate;
    private TransactionState transactionState;
    private List<OrderDetailsRegistration> orderDetailsRegistrationList;
    private List<ShippingDetailsRegistration> shippingDetailsRegistrationList;
}
