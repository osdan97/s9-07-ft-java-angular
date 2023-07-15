package com.nocountry.ecommerce.dto;

import com.nocountry.ecommerce.util.enums.PhoneLabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhonesDto {
    private PhoneLabel phoneLabel;
    private String phoneNumber;
    private String cityCode;
    private String countryCode;
}
