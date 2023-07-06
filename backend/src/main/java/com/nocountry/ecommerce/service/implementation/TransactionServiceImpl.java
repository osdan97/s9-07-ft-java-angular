package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.TransactionDto;
import com.nocountry.ecommerce.dto.TransactionInventoryProdUpdate;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.model.Transaction;
import com.nocountry.ecommerce.repository.InventoryRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.repository.TransactionRepository;
import com.nocountry.ecommerce.service.TransactionService;
import com.nocountry.ecommerce.util.enums.TransactionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryServiceImpl inventoryServiceimpl;

    @Autowired
    ProductServiceImpl productServiceimpl;

    @Override
    public Transaction createTransaction(TransactionDto transactionDto){


        String transaction_uuid= UUID.randomUUID().toString();
        Transaction saveTransaction=new Transaction();
        saveTransaction.setId(transaction_uuid);
        saveTransaction.setCreatedDate(LocalDateTime.now());

        String name_operation=transactionDto.getName_operation();
        saveTransaction.setName_operation(name_operation);

        String description=transactionDto.getDescription();
        saveTransaction.setDescription(description);

        int cant_transaccion=transactionDto.getCant();
        if(cant_transaccion>0){
            saveTransaction.setCant(cant_transaccion);

        }else{
            throw new IllegalStateException("amount not allowed");

        }


        Inventory inventoryEntity= inventoryRepository.getByName(transactionDto.getInventory());
        saveTransaction.setInventory(inventoryEntity);

        Product productEntity=productRepository.getByName(transactionDto.getProduct());
        saveTransaction.setProduct(productEntity);

        //entranceInventory(inventoryEntity,cant_transaccion);
        if(name_operation.equals(TransactionOperation.ENTRANCE.toString()) ){


            entranceInventory(inventoryEntity,cant_transaccion);

        } else if ((name_operation.equals(TransactionOperation.EXIT.toString()) )) {

            exitProductInventory(inventoryEntity,productEntity,cant_transaccion);
        }


        transactionRepository.save(saveTransaction);
        return  saveTransaction;

    }
    @Override
    public Transaction updateTransaction
            (String id , TransactionInventoryProdUpdate tIProdUpdate){

        Transaction updated_transaction=transactionRepository.getById(id);
        updated_transaction.setDescription(tIProdUpdate.getDescription());
        return updated_transaction;

    }

    @Override
    public List<Transaction> listTransaction(){
        return transactionRepository.findAll();
    }

    /*public void deleteTransaction(){

    } */
    public void entranceInventory(Inventory inventoryEntity,int cant){
        int cant_inventory=inventoryEntity.getCant();
        cant_inventory+=cant;

        InventoryDto updated_inventory=new InventoryDto();
        updated_inventory.setName(inventoryEntity.getName());
        updated_inventory.setCant(cant_inventory);
        inventoryServiceimpl.updateInventory(inventoryEntity.getId(),updated_inventory);
    }

    public void exitProductInventory(Inventory inventoryEntity,Product productEntity,int cant){
        int cant_inventory=inventoryEntity.getCant();
        int stock_product=productEntity.getStock();
        int diferencia=cant_inventory-cant;
        if(diferencia<0){
            throw new IllegalStateException("QUANTITY NOT ALLOWED, CHECK YOUR INVENTORY STOCK");
        } else if (diferencia==0) {
            throw new IllegalStateException("QUANTITY NOT ALLOWED, CHECK YOUR INVENTORY STOCK");
        }else{
            cant_inventory-=cant;

            InventoryDto updated_inventory=new InventoryDto();
            updated_inventory.setName(inventoryEntity.getName());
            updated_inventory.setCant(cant_inventory);
            inventoryServiceimpl.updateInventory(inventoryEntity.getId(),updated_inventory);

            stock_product+=cant;

            Product updated_product=new Product();
            updated_product.setId(productEntity.getId());
            updated_product.setDescription(productEntity.getDescription());
            updated_product.setName(productEntity.getName());
            updated_product.setState(productEntity.getState());
            updated_product.setCategory(productEntity.getCategory());
            updated_product.setImage(productEntity.getImage());
            updated_product.setCountry(productEntity.getCountry());
            updated_product.setPrice(productEntity.getPrice());
            updated_product.setMinStock(productEntity.getMinStock());
            updated_product.setWeight(productEntity.getWeight());
            updated_product.setStock(stock_product);

            productRepository.save(updated_product);
        }

    }


}
