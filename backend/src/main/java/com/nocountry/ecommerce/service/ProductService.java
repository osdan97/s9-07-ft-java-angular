package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product updateProduct(String id, ProductDto productDto);
    void deleteProduct(String id);
    List<Product> getProducts(ProductPageble productPageble);
}

