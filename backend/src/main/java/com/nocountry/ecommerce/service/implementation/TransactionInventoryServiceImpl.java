package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.TransactionInventoryRegisterDto;
import com.nocountry.ecommerce.dto.TransactionsDto;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.model.TransactionInventory;
import com.nocountry.ecommerce.repository.InventoryRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.repository.TransactionInventoryRepository;
import com.nocountry.ecommerce.service.TransactionInventoryService;
import com.nocountry.ecommerce.util.enums.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionInventoryServiceImpl implements TransactionInventoryService {

    @Autowired
    TransactionInventoryRepository transactionInventoryRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryServiceImpl inventoryServiceimpl;
    @Override
    public TransactionInventory createTransactionInventory(TransactionInventoryRegisterDto transactionInventoryRegisterDto) {

        TransactionInventory saveTransaction = new TransactionInventory();

        LocalDateTime createdDate = LocalDateTime.now();
        saveTransaction.setCreatedDate(createdDate);
        saveTransaction.setTransactionState(TransactionState.COMPLETED);

        String description=transactionInventoryRegisterDto.getDescription();
        saveTransaction.setDescription(description);

        String transaction_type= transactionInventoryRegisterDto.getTransaction_type();
        saveTransaction.setTransaction_type(transaction_type);

        Integer quantity= transactionInventoryRegisterDto.getQuantity();
        saveTransaction.setQuantity(quantity);

        Inventory inventory=inventoryRepository.getByName(transactionInventoryRegisterDto.getInventory());
        saveTransaction.setInventory(inventory);

        Double purcharse_price=inventoryRepository.getByName(transactionInventoryRegisterDto.getInventory()).getPurchase_price();
        Double total=calculateTotal(purcharse_price,quantity);
        saveTransaction.setTotal(total);

        Product product=inventory.getProduct();

        int currentYear = LocalDate.now().getYear();
        String numeration = obtenerNumeracionAutomatica();
        String number = currentYear + "-" + numeration;
        saveTransaction.setNumber(number);

        String transactionUuid = UUID.randomUUID().toString();
        saveTransaction.setTransactionUuid(transactionUuid);

        entranceInventory(inventory,product,quantity);
        transactionInventoryRepository.save(saveTransaction);

        return saveTransaction;
    }

    /*@Override
    public TransactionInventory updateTransactionInventory(String id, TransactionInventoryRegisterDto transactionInventoryRegisterDto) {

        if(!transactionInventoryRepository.existsById(id)){
            throw new IllegalStateException("Inventory transaction does not exists");
        }
        Optional<TransactionInventory> existingTransactionInventory = transactionInventoryRepository.findById(id);

        TransactionInventory updatedTransaction = existingTransactionInventory.get();
       //Solo se puede actualizar descripcion y tipo de transaccion(compra o devolucion)
        String transaction_type= transactionInventoryRegisterDto.getTransaction_type();
        String description=transactionInventoryRegisterDto.getDescription();
        updatedTransaction.setTransaction_type(transaction_type);
        updatedTransaction.setDescription(description);

        transactionInventoryRepository.save(updatedTransaction);
        return updatedTransaction;
    }*/



    @Override
    public List<TransactionInventory> listTransactionsInventory() {
        return transactionInventoryRepository.findAll();
    }

    private String obtenerNumeracionAutomatica() {
        String maxNumber = transactionInventoryRepository.findByNumber();
        if (maxNumber == null || maxNumber.isEmpty()) {
            return "1";
        } else {
            int separatorIndex = maxNumber.indexOf("-");
            if (separatorIndex != -1 && separatorIndex + 1 < maxNumber.length()) {
                String numeracion = maxNumber.substring(separatorIndex + 1);
                int number = Integer.parseInt(numeracion.trim());
                number++;
                return String.valueOf(number);
            } else {
                return "1";
            }
        }
    }
    private double calculateTotal(Double price, int quantity) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double amount = price * quantity;
        return Double.parseDouble(decimalFormat.format(amount));
    }

    public void entranceInventory(Inventory inventoryEntity, Product productEntity, int quantity_transfer){
        int stock_inventory=inventoryEntity.getStock_inventory();
        stock_inventory+=quantity_transfer;
        inventoryEntity.setStock_inventory(stock_inventory);
        inventoryRepository.save(inventoryEntity);

        int stock_product=productEntity.getStock();
        stock_product+=quantity_transfer;
        productEntity.setStock(stock_product);
        productRepository.save(productEntity);

    }

}
