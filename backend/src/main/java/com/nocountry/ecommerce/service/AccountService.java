package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.dto.CustomerUpdate;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface AccountService {
    CustomerRegistration createCustomer(Customers customers);
    Optional<Account> findByEmail(String email);
    CustomerUpdate updateCustomer (Customers customer);

    @Transactional
    boolean verifyAccount(String verificationCode);
}
