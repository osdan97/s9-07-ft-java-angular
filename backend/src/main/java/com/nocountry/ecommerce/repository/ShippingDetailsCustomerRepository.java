package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShippingDetailsCustomerRepository extends JpaRepository<ShippingDetailsCustomer, String> {
    @Query(
            value = "select * from ShippingDetailsCustomer s where s.accountUuid=:customerUuid",
            nativeQuery = true
    )
    List<ShippingDetailsCustomer> findByCustomerAccountUuid(String customerUuid);
}
