package com.nocountry.ecommerce.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransactionState {
    ON_HOLD,
    COMPLETED;
    private String value;
}
