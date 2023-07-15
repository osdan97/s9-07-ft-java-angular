package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.ShippingDetailsCustomerName;
import com.nocountry.ecommerce.dto.ShippingDetailsCustomerRegistration;
import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ShippingDetailsCustomerService {
    @Transactional
    ShippingDetailsCustomerRegistration addShippingAddress(String accountUuid, ShippingDetailsCustomer shippingDetailsDTO);

    void changeToPrimaryAddress(String accountUuid, String shippingDetailUuid);

    List<ShippingDetailsCustomerName> findShippingDetailsCustomerByName(String customerUuid);
}
