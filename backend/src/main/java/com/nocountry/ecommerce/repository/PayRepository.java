package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PayRepository extends JpaRepository<Pay, String> {
    @Query(value = "SELECT MAX(a.number) FROM ACCOUNT a WHERE a.entity = 'pay'", nativeQuery = true)
    String findByNumber();
}
