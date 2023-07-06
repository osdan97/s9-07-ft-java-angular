package com.nocountry.ecommerce.service.implementation;


import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.InventoryRepository;
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
    @Override
    public Inventory createInventory(InventoryDto inventoryDto) {
        Inventory saveInventory = new Inventory();
        String uuid = UUID.randomUUID().toString();
        saveInventory.setId(uuid);
        String name = inventoryDto.getName();
        saveInventory.setName(name);

        int cant = inventoryDto.getCant();
        saveInventory.setCant(cant);

        LocalDateTime update_date=LocalDateTime.now();
        saveInventory.setUpdateDate(update_date);

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

        String name = inventoryDto.getName();
        inventoryUpdated.setName(name);
        LocalDateTime update_date = LocalDateTime.now();
        inventoryUpdated.setUpdateDate(update_date);
        int cant = inventoryDto.getCant();
        inventoryUpdated.setCant(cant);

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
}
