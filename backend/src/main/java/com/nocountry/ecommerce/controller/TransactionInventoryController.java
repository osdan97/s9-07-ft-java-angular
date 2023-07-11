package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.TransactionInventoryRegisterDto;
import com.nocountry.ecommerce.service.TransactionInventoryService;
import com.nocountry.ecommerce.service.implementation.TransactionInventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction/inventory")
public class TransactionInventoryController {
    @Autowired
    TransactionInventoryService transactionInventoryService;

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestBody TransactionInventoryRegisterDto transactionInventoryRegisterDto){
        return new ResponseEntity<>(transactionInventoryService.createTransactionInventory(transactionInventoryRegisterDto), HttpStatus.CREATED);

    }
    @GetMapping("list")
    public ResponseEntity<?> getTransactionsInventory(){
        return new ResponseEntity<>(transactionInventoryService.listTransactionsInventory(), HttpStatus.CREATED);

    }
}
