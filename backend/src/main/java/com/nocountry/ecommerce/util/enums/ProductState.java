package com.nocountry.ecommerce.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductState {
    UNAVAILABLE("U"),
    WARNING("W"),
    AVAILABLE("A"),;

    private String product;
}
