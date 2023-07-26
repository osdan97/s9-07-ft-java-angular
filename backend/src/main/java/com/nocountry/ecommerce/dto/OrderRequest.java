package com.nocountry.ecommerce.dto;

import com.nocountry.ecommerce.util.enums.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String number;
    private Double shippingCost;
    private Double amountTaxes;
    private Double amountTotal;
    private Double total;
    private long trackingNumber;
    private LocalDateTime createdDate;
    private TransactionState transactionState;
}
