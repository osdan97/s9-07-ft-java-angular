package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.ShippingDetailsCustomerName;
import com.nocountry.ecommerce.dto.ShippingDetailsCustomerRegistration;
import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import com.nocountry.ecommerce.service.ShippingDetailsCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping-details")
public class ShippingDetailsCustomerController {
    @Autowired
    private ShippingDetailsCustomerService shippingDetailsService;

    @PostMapping("/{accountUuid}")
    public ResponseEntity<ShippingDetailsCustomerRegistration> addShippingAddress(@PathVariable("accountUuid") String accountUuid, @RequestBody ShippingDetailsCustomer shippingDetailsDTO) {
        return new ResponseEntity<>(shippingDetailsService.addShippingAddress(accountUuid, shippingDetailsDTO), HttpStatus.OK);
    }
    @PostMapping("/change-primary/{accountUuid}/{shippingDetailUuid}")
    public ResponseEntity<String> changeToPrimaryAddress(@PathVariable String accountUuid, @PathVariable String shippingDetailUuid) {
        shippingDetailsService.changeToPrimaryAddress(accountUuid, shippingDetailUuid);
        return ResponseEntity.ok("Address changed to primary successfully.");
    }

    @GetMapping("/customer/{customerUuid}")
    public ResponseEntity<List<ShippingDetailsCustomerName>> findShippingDetailsCustomerByName(@PathVariable String customerUuid) {
        List<ShippingDetailsCustomerName> shippingDetailsDTOList = shippingDetailsService.findShippingDetailsCustomerByName(customerUuid);
        return ResponseEntity.ok(shippingDetailsDTOList);
    }
}
