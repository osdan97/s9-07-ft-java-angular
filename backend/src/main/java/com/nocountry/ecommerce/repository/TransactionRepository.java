package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, String> {
    @Query(value = "SELECT * FROM ecommercedb.TRANSACTIONS o WHERE account_uuid=:accountUuid", nativeQuery = true)
    List<Orders> findByAccountUuid(@Param("accountUuid") String accountUuid);
}
