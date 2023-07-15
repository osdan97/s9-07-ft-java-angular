package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, String> {
}
