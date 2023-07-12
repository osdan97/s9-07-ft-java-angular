package com.nocountry.ecommerce.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetailsRegistration {
    private String fullName;
    private String address;
    private String address2;
    private String postalCode;
    private String provincia;
    private String city;
    private String country;
}
