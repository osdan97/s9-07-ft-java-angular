package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.util.enums.TransactionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    @Query(value = "SELECT MAX(a.number) FROM TRANSACTIONS a WHERE a.transaction = 'order'", nativeQuery = true)
    String findByNumber();
    @Modifying
    @Query(value = "UPDATE ecommercedb.TRANSACTIONS SET state=:transaction_state WHERE transaction_uuid=:transaction",
            nativeQuery = true)
    void changeState(@Param("transaction") String transaction, @Param("transaction_state") String transaction_state);
}
