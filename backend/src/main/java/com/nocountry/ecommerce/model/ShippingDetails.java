package com.nocountry.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "SHIPPINGDETAILS")
public class ShippingDetails implements Serializable {
    @Id
    @Column(name = "shippingdetail_uuid")
    private String shippingDetailUuid;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "company")
    private String company;
    @Column(name = "address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "gift")
    private boolean gift;
}
