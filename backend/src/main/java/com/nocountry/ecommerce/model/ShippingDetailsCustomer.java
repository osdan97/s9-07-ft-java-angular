package com.nocountry.ecommerce.model;

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
@Table(name = "SHIPPING_DETAILS_CUSTOMER")
public class ShippingDetailsCustomer implements Serializable {
    @Id
    @Column(name = "shippingdetail_uuid")
    private String shippingDetailUuid;

    @Column(name = "shippingdetails_name", unique = true)
    private String shippingDetailsName;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @ManyToOne
    private Customers customer;
    @Column(name = "active")
    private boolean active;
    @Column(name = "primary_address")
    private boolean primaryAddress;
    @Column(name = "gift")
    private boolean gift;
}
