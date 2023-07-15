package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.TransactionInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  TransactionInventoryRepository extends JpaRepository<TransactionInventory,String> {


    @Query(value = "SELECT MAX(a.number) FROM TRANSACTIONS a WHERE a.transaction = 'transactionInventory'", nativeQuery = true)
    String findByNumber();
}
