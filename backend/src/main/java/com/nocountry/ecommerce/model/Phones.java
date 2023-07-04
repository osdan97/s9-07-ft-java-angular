package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.PhoneLabel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PHONE")
public class Phones implements Serializable {
    @Id
    private String phoneUuid;
    @Enumerated(EnumType.STRING)
    private PhoneLabel phoneLabel;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
}
