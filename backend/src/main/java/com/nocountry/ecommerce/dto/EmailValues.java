package com.nocountry.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailValues {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String fullName;
    private String token;
}