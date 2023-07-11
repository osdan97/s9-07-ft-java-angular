package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingDetailsCustomerRepository extends JpaRepository<ShippingDetailsCustomer, String> {
    List<ShippingDetailsCustomer> findByCustomerAccountUuid(String customerUuid);
}
