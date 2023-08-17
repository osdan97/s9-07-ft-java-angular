package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> updateCustomer(@PathVariable String uuid, @RequestBody Customers customer){
        customer.setAccountUuid(uuid);
        return new ResponseEntity<>(accountService.updateCustomer(customer), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?> listCustomer(){
        List<Customers> list=customerRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
