package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.TransactionInventoryRegisterDto;
import com.nocountry.ecommerce.dto.TransactionsDto;
import com.nocountry.ecommerce.model.TransactionInventory;

import java.util.List;
import java.util.Optional;

public interface TransactionInventoryService {

    TransactionInventory createTransactionInventory(TransactionInventoryRegisterDto transactionInventoryRegisterDto);
   // TransactionInventory updateTransactionInventory(String name,TransactionInventoryRegisterDto transactionInventoryRegisterDto );

    List<TransactionInventory> listTransactionsInventory();
}
