package com.nocountry.ecommerce.service.implementation;


import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.InventoryRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.service.InventoryService;
import com.nocountry.ecommerce.util.enums.ProductState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public Inventory createInventory(InventoryDto inventoryDto) {
        Inventory saveInventory = new Inventory();
        String uuid = UUID.randomUUID().toString();
        saveInventory.setId(uuid);
        String name = inventoryDto.getName();
        saveInventory.setName(name);

        String image=inventoryDto.getImage();
        saveInventory.setImage(image);

        Double purchase_price=inventoryDto.getPurchase_price();
        saveInventory.setPurchase_price(purchase_price);

        String product_name=inventoryDto.getProduct_name();
        Product product=productRepository.getByName(product_name);
        saveInventory.setProduct(product);

        Double selling_price=productRepository.getByName(product_name).getPrice();
        saveInventory.setSelling_price(selling_price);

        int stock_inventory = inventoryDto.getStock_inventory();
        saveInventory.setStock_inventory(stock_inventory);

        LocalDateTime update_date=LocalDateTime.now();
        saveInventory.setUpdateDate(update_date);

        changeStockProduct(product,stock_inventory);

        inventoryRepository.save(saveInventory);
        return saveInventory;
    }



    @Override
    public Inventory getByName(String name){

        return inventoryRepository.getByName(name);
    }


    @Override
    public Inventory updateInventory(String id, InventoryDto inventoryDto) {
        if(!inventoryRepository.existsById(id)){
            throw new IllegalStateException("Inventory does not exist");
        }
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);
        Inventory inventoryUpdated = existingInventory.get();

        inventoryUpdated.setPurchase_price(inventoryDto.getPurchase_price());
        inventoryUpdated.setImage(inventoryDto.getImage());
//Ni el producto ni el stock , se pueden actualizar en el inventario,solo el nombre , el precio de compra
        //y la imagen
        //LocalDateTime update_date = LocalDateTime.now();
        //inventoryUpdated.setUpdateDate(update_date);


        inventoryRepository.save(inventoryUpdated);
        return inventoryUpdated;
    }

    @Override
    public List<Inventory> list(){
        return inventoryRepository.findAll();
    }
    @Override
    public void deleteInventory(String id) {
        if(!inventoryRepository.existsById(id)){
            throw new IllegalStateException("Inventory does not exist");
        }
        inventoryRepository.deleteById(id);
    }

    public void changeStockProduct(Product productEntity,int stock){
        productEntity.setStock(stock);
        productRepository.save(productEntity);
    }
}
