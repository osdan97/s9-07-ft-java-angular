package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Users;
import com.nocountry.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private AccountService accountService;



    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody Users user){
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Email can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            return new ResponseEntity<>("Name can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            return new ResponseEntity<>("Lastname can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (accountService.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>("This account already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(accountService.createUser(user), HttpStatus.CREATED);
        }
    }
}
