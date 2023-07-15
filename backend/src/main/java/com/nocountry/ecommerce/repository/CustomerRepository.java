package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface      CustomerRepository extends JpaRepository<Customers, String> {
    Optional<Customers> findByEmail(String email);

    Optional<Customers> findByAccountUuid(String uuid);
}
