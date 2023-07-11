package com.nocountry.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInventoryRegisterDto {
   private String number;
   private Integer quantity;
   private String inventory;
   private String transaction_type;
   private String description;

}
