package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
    @Autowired
    private AccountService accountService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody Customers customer){
            if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
                return new ResponseEntity<>("Email can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
                return new ResponseEntity<>("Password can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (customer.getName() == null || customer.getName().isEmpty()) {
                return new ResponseEntity<>("Name can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
                return new ResponseEntity<>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (accountService.findByEmail(customer.getEmail()).isPresent()) {
                return new ResponseEntity<>("This account already exists", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(accountService.createCustomer(customer), HttpStatus.CREATED);
            }
    }
}
