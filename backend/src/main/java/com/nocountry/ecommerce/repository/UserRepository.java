package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
}
