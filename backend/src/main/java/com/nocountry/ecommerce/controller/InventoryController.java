package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.service.InventoryService;
import com.nocountry.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<?> saveProduct(@RequestBody InventoryDto inventoryDto){
        return new ResponseEntity<>(inventoryService.createInventory(inventoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody InventoryDto inventoryDto){
        return new ResponseEntity<>(inventoryService.updateInventory(id,inventoryDto), HttpStatus.OK);
    }


   /* @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>("Inventory delete successfully", HttpStatus.OK);
    }*/

    @GetMapping("/list")
    public ResponseEntity<?> listInventory(){
        List<Inventory> list = inventoryService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
