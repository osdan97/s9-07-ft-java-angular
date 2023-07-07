package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.ChangePassword;
import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.dto.CustomerUpdate;
import com.nocountry.ecommerce.dto.EmailValues;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface AccountService {
    CustomerRegistration createCustomer(Customers customers);
    Optional<Account> findByEmail(String email);

    abstract Optional<Account> findByTokenPassword(String tokenPassword);

    CustomerUpdate updateCustomer (Customers customer);

    @Transactional
    boolean verifyAccount(String verificationCode);

    abstract EmailValues sendPasswordRecoveryToEmail(Customers emailRecoverPass) throws MessagingException, UnsupportedEncodingException;

    Account changePassword(ChangePassword changePassword);

    Account findByUsernameReturnToken(String username);
}

