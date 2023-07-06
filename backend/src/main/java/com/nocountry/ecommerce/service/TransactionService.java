package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.TransactionDto;
import com.nocountry.ecommerce.dto.TransactionInventoryProdUpdate;
import com.nocountry.ecommerce.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(TransactionDto transactionDto);
    public Transaction updateTransaction
            (String id , TransactionInventoryProdUpdate tIProdUpdate);

    List<Transaction> listTransaction();
}
