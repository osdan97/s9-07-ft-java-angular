package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  ShippingDetailsCustomerRepository extends JpaRepository<ShippingDetailsCustomer, String> {
    @Query(
            value = "select * from SHIPPING_DETAILS_CUSTOMER s where s.account_uuid=:customerUuid",
            nativeQuery = true
    )
    List<ShippingDetailsCustomer> findByCustomerAccountUuid(String customerUuid);
    @Query(
            value = "select * from SHIPPING_DETAILS_CUSTOMER s where s.account_uuid=:customerUuid and s.shippingdetail_uuid =:shipping_uuid",
            nativeQuery = true
    )
    ShippingDetailsCustomer findByCustomerAccountUuidAndShippingDetailUuid(String customerUuid, String shipping_uuid);
}
