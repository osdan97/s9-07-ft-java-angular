package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.TransactionDto;
import com.nocountry.ecommerce.dto.TransactionInventoryProdUpdate;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Transaction;
import com.nocountry.ecommerce.service.InventoryService;
import com.nocountry.ecommerce.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction/ip")
public class TransactionInveProdController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody TransactionInventoryProdUpdate transactionDto){
        return new ResponseEntity<>(transactionService.updateTransaction(id,transactionDto), HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<?> listTransaction(){
        List<Transaction> list = transactionService.listTransaction();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
