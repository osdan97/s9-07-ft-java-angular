package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    @Query(value = "SELECT MAX(a.number) FROM ecommercedb.TRANSACTIONS a WHERE a.transaction = 'order'", nativeQuery = true)
    String findByNumber();
}
