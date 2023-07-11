package com.nocountry.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetailsCustomerRegistration {

    private String name;
    private String lastName;
    private String shippingDetailsName;
    private String address1;
    private String address2;
    private String postalCode;
    private String provincia;
    private String city;
    private String country;
    private boolean active;
    private boolean primaryAddress;
    private boolean gift;
}
