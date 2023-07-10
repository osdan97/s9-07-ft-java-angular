package com.nocountry.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerUpdate {
    private String name;
    private String lastName;
    private String country;
    private String address;
    private List<PhonesDto> phonesList;
}
