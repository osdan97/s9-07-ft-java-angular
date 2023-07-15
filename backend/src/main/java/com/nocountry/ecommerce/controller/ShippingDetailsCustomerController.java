package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.Mensaje;
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
        try {
            return new ResponseEntity<>(shippingDetailsService.addShippingAddress(accountUuid, shippingDetailsDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-primary/{accountUuid}/{shippingDetailUuid}")
    public ResponseEntity<String> changeToPrimaryAddress(@PathVariable String accountUuid, @PathVariable String shippingDetailUuid) {
        try {
            shippingDetailsService.changeToPrimaryAddress(accountUuid, shippingDetailUuid);
            return ResponseEntity.ok("Address changed to primary successfully.");
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Error occurred while changing address to primary."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{customerUuid}")
    public ResponseEntity<List<ShippingDetailsCustomerName>> findShippingDetailsCustomerByName(@PathVariable String customerUuid) {
            List<ShippingDetailsCustomerName> shippingDetailsDTOList = shippingDetailsService.findShippingDetailsCustomerByName(customerUuid);
            return ResponseEntity.ok(shippingDetailsDTOList);
    }
}
