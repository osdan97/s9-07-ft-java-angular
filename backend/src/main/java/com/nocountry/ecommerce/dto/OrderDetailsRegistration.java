package com.nocountry.ecommerce.dto;

import com.nocountry.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsRegistration {
    private String productName;
    private int quantity;
    private Double price;
    private Double taxes;
    private Double total;
}
