package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product updateProduct(String id, ProductDto productDto);
    void changeStateProduct (String id);
    void deleteProduct(String id);
    List<Product> getProducts(ProductPageble productPageble);

    Optional<Product> getProduct(String product);

    Optional<Product> getProductByUuid(String id);
}

