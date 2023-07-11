package com.nocountry.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private String name;
    private int stock_inventory;
    private String product_name;
    private String image;
    private Double purchase_price;

}
