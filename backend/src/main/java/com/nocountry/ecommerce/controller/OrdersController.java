package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrdersController {
    @Autowired
    private OrdersService orderService;
    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestBody Orders orders) {
        return new ResponseEntity<>(orderService.createOrder(orders), HttpStatus.CREATED);
    }
}
