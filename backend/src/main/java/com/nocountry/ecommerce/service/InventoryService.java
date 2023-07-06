package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.model.Inventory;

import java.util.List;

public interface InventoryService  {

    List<Inventory> list();
    Inventory updateInventory(String id, InventoryDto dtoInventory);
    void deleteInventory(String id);
    Inventory createInventory(InventoryDto dtoInventory);
    Inventory getByName(String name);

}
