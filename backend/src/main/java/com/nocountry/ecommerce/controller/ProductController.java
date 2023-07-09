package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @PatchMapping("/update-state/{id}")
    public ResponseEntity<?> chageStateProduct(@PathVariable String id){
        productService.changeStateProduct(id);
        return new ResponseEntity<>("The product state changed", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product delete successfully", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getProducts(@RequestBody ProductPageble productPageble){
        return new ResponseEntity<>(productService.getProducts(productPageble), HttpStatus.OK);
    }
}
