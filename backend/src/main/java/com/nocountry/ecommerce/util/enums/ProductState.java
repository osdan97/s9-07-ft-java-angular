package com.nocountry.ecommerce.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ProductState {
    U("UNAVAILABLE"),
    W("WARNING"),
    A("AVAILABLE");

    private String product;
}
