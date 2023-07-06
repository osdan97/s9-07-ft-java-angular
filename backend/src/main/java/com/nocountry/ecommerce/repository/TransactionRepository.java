package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
