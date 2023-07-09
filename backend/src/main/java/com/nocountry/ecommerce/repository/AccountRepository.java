package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByTokenPassword(String token);

    @Query(value = "SELECT MAX(a.number) FROM ACCOUNT a WHERE a.entity = 'customer'", nativeQuery = true)
    String findByNumber();

    @Query("select a from Account a where a.verificationCode=:verificationCode")
    Account findByVerificationCode(@Param("verificationCode") String verificationCode);

    @Modifying
    @Query("update Account set verificationCode=null where email=:email")
    void updateVerificationCode(@Param("email") String email);
}
