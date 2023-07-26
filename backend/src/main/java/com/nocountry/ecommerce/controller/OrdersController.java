package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.dto.OrderRequest;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.service.OrdersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@SecurityRequirement(name = "jwt")
public class OrdersController {
    @Autowired
    private OrdersService orderService;
    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestBody Orders orders) {
        try {
            return new ResponseEntity<>(orderService.createOrder(orders), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/by-account/{accountUuid}")
    public ResponseEntity<?> getOrdersByAccountUuid(@PathVariable String accountUuid) {
        try {
            List<OrderRequest> orders = orderService.getOrdersByAccountUuid(accountUuid);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
