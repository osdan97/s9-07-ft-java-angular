package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;

import java.util.Optional;

public interface AccountService {
    CustomerRegistration createCustomer(Customers customers);

    Optional<Account> findByEmail(String email);
}
