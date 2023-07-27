package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.Mensaje;
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
    @PostMapping("/add")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            if(productDto.getName() == null || productDto.getName().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Product name can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if (productDto.getDescription() == null || productDto.getDescription().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Description can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getImage() == null || productDto.getImage().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Image can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getPrice() == null || productDto.getPrice().isNaN()){
                return new ResponseEntity<>(new Mensaje("Price can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getWeight() == null || productDto.getWeight().isNaN()){
                return new ResponseEntity<>(new Mensaje("Weight can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getCountry() == null || productDto.getCountry().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Country can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getMinStock() == null){
                return new ResponseEntity<>(new Mensaje("Minimum stock can't be empty"), HttpStatus.BAD_REQUEST);
            }
            if(productDto.getCategory() == null || productDto.getCategory().isEmpty()){
                return new ResponseEntity<>(new Mensaje("Country can't be empty"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "jwt")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        try{
            if(id == null){
                return new ResponseEntity<>(new Mensaje("Id can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                if(productService.getProduct(id).isPresent()){
                    return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Product not found"), HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "jwt")
    @PatchMapping("/update-state/{id}")
    public ResponseEntity<?> changeStateProduct(@PathVariable String id) {
        try {
            if (id == null) {
                return new ResponseEntity<>(new Mensaje("Id can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                if (productService.getProduct(id).isPresent()) {
                    productService.changeStateProduct(id);
                    return new ResponseEntity<>(new Mensaje("Product state changed"), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Product not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SecurityRequirement(name = "jwt")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        try{
            if(id == null){
                return new ResponseEntity<>(new Mensaje("Id can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                if(productService.getProduct(id).isPresent()) {
                    productService.deleteProduct(id);
                    return new ResponseEntity<>(new Mensaje("Product delete successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Product not found"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProducts(@RequestParam("page") Integer page,
                                         @RequestParam(value = "country", required = false) String country,
                                         @RequestParam(value = "category",required = false) String category){
        try{
            if(page == null){
                return new ResponseEntity<>(new Mensaje("Page can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(page < 1){
                return new ResponseEntity<>(new Mensaje("Page can't be less than 1"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(productService.getProducts(page, country, category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalproducts")
    public ResponseEntity<?> getTotalProducts(@RequestParam("page") Integer page,
                                              @RequestParam(value = "country", required = false) String country,
                                              @RequestParam(value = "category",required = false) String category){
        try{
            if(page == null){
                return new ResponseEntity<>(new Mensaje("Page can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(page < 1){
                return new ResponseEntity<>(new Mensaje("Page can't be less than 1"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(productService.getTotalProducts(page, country, category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalpages")
    public ResponseEntity<?> getTotalPages(@RequestParam("page") Integer page,
                                           @RequestParam(value = "country", required = false) String country,
                                           @RequestParam(value = "category",required = false) String category){
        try{
            if(page == null){
                return new ResponseEntity<>(new Mensaje("Page can't be null"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(page < 1){
                return new ResponseEntity<>(new Mensaje("Page can't be less than 1"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(productService.getTotalPage(page, country, category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
            return new ResponseEntity<>(productService.getProductByUuid(id), HttpStatus.OK);
    }
    @GetMapping("/name/{producto}")
    public ResponseEntity<?> getProductByName(@PathVariable String producto){
        return new ResponseEntity<>(productService.findProductByName(producto), HttpStatus.OK);
    }
}
