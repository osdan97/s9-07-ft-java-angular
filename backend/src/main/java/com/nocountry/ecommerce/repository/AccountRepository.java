package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);

    @Query(value = "SELECT MAX(a.number) FROM Account a WHERE a.DTYPE = 'customer'", nativeQuery = true)
    String findByNumber();
}
