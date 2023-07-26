package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Pay;
import com.nocountry.ecommerce.service.PayService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/pay")
public class PaymentController {
    @Autowired
    private PayService payService;

    @PostMapping()
    public ResponseEntity<?> payment(@RequestBody Pay pay) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<>(payService.chargePay(pay), HttpStatus.OK);
    }
}
