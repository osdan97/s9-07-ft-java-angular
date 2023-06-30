package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepository extends JpaRepository<Customers, String> {
}
