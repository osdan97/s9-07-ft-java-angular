package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private AccountService accountService;

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> updateCustomer(@PathVariable String uuid, @RequestBody Customers customer){
        customer.setAccountUuid(uuid);
        return new ResponseEntity<>(accountService.updateCustomer(customer), HttpStatus.OK);
    }
}
