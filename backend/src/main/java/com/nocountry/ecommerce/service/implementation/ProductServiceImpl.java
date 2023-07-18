package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.ProductDto;
import com.nocountry.ecommerce.dto.ProductPageble;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.CategoryRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.service.ProductService;
import com.nocountry.ecommerce.util.enums.ProductState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class   ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(ProductDto productDto) {
        Product saveProduct = new Product();
        String uuid = UUID.randomUUID().toString();
        saveProduct.setId(uuid);
        String name = productDto.getName();
        saveProduct.setName(name);
        String description = productDto.getDescription();
        saveProduct.setDescription(description);
        saveProduct.setStock(0);
        String image = productDto.getImage();
        saveProduct.setImage(image);
        Double price = productDto.getPrice();
        saveProduct.setPrice(price);
        Double weight = productDto.getWeight();
        saveProduct.setWeight(weight);
        String country = productDto.getCountry();
        saveProduct.setCountry(country);
        Integer minStock = productDto.getMinStock();
        saveProduct.setMinStock(minStock);
        saveProduct.setState(ProductState.U);
        String category = productDto.getCategory();
        Category categoryEntity = categoryRepository.getByName(category);

        if(categoryEntity == null){
            throw new IllegalStateException("Category can't be empty");
        }
        saveProduct.setCategory(categoryEntity);

        productRepository.save(saveProduct);
        return saveProduct;
    }

    @Override
    public Product updateProduct(String id, ProductDto productDto) {
        if(!productRepository.existsById(id)){
            throw new IllegalStateException("Product does not exists");
        }
        Optional<Product> existingProduct = productRepository.findById(id);
        Product productUpdated = existingProduct.get();

        String name = productDto.getName();
        productUpdated.setName(name);
        String description = productDto.getDescription();
        productUpdated.setDescription(description);
        String image = productDto.getImage();
        productUpdated.setImage(image);
        Double price = productDto.getPrice();
        productUpdated.setPrice(price);
        Double weight = productDto.getWeight();
        productUpdated.setWeight(weight);
        String country = productDto.getCountry();
        productUpdated.setCountry(country);
        String category = productDto.getCategory();
        Category categoryEntity = categoryRepository.getByName(category);
        productUpdated.setCategory(categoryEntity);

        productRepository.save(productUpdated);
        return productUpdated;
    }

    @Override
    public void changeStateProduct(String id) {
        if(!productRepository.existsById(id)){
            throw new IllegalStateException("Product does not exists");
        }
        Optional<Product> existingProduct = productRepository.findById(id);
        Product productToChangeState = existingProduct.get();

        Integer stock = productToChangeState.getStock();
        Integer minStock = productToChangeState.getMinStock();

        if(stock > minStock) {
            productToChangeState.setState(ProductState.A);
        }
        if(stock < minStock) {
            productToChangeState.setState(ProductState.W);
        }
        if(stock == 0) {
            productToChangeState.setState(ProductState.U);
        }
        productRepository.save(productToChangeState);
    }

    @Override
    public void deleteProduct(String id) {
        if(!productRepository.existsById(id)){
            throw new IllegalStateException("Product does not exists");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProducts(ProductPageble productPageble) {
        PageRequest pageRequest = PageRequest.of(productPageble.getPage() - 1, 8, Sort.by("name").ascending());
        Page<Product> productPage;
        String category = productPageble.getCategory();
        String country = productPageble.getCountry();

        if(category == null && country != null){
            productPage = productRepository.findAllByCountry(country,pageRequest);
            return productPage.getContent();
        }
        if(category != null && country == null){
            productPage = productRepository.findAllByCategoryName(category, pageRequest);
            return productPage.getContent();
        }
        if(category != null && country != null){
            productPage = productRepository.findAllByCountryAndCategoryName(country,category, pageRequest);
            return productPage.getContent();
        }

        productPage = productRepository.findAll(pageRequest);
        return productPage.getContent();
    }

    @Override
    public Optional<Product> getProduct(String productName) {
        return productRepository.findByName(productName);
    }
    @Override
    public Optional<Product> getProductByUuid(String id) {
        return productRepository.findById(id);
    }

    //TODO: implement max pages for product and total products
}
