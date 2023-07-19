package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.InventoryDto;
import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.service.InventoryService;
import com.nocountry.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@SecurityRequirement(name = "jwt")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(summary = "Add a new Inventory",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Inventory created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Inventory already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
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
   @Operation(summary = "Get Inventory", responses = {
           @ApiResponse(responseCode = "200", description = "Inventory list",
                   content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
           @ApiResponse(responseCode = "404", description = "Favorites not found",
                   content = @Content(mediaType = "application/json", schema = @Schema (ref = "#/components/schemas/Error")))})
    @GetMapping("/list")
    public ResponseEntity<?> listInventory(){
        List<Inventory> list = inventoryService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
