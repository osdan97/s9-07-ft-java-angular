package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
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

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Add a new Product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Product already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @SecurityRequirement(name = "jwt")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @PatchMapping("/update-state/{id}")
    public ResponseEntity<?> changeStateProduct(@PathVariable String id){
        productService.changeStateProduct(id);
        return new ResponseEntity<>("Product state changed", HttpStatus.OK);
    }

    @SecurityRequirement(name = "jwt")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product delete successfully", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProducts(@RequestBody ProductPageble productPageble){
        return new ResponseEntity<>(productService.getProducts(productPageble), HttpStatus.OK);
    }

    @GetMapping("/totalproducts")
    public ResponseEntity<?> getTotalProducts(@RequestBody ProductPageble productPageble){
        return new ResponseEntity<>(productService.getTotalProducts(productPageble), HttpStatus.OK);
    }

    @GetMapping("/totalpages")
    public ResponseEntity<?> getTotalPages(@RequestBody ProductPageble productPageble){
        return new ResponseEntity<>(productService.getTotalPage(productPageble), HttpStatus.OK);
    }
}
