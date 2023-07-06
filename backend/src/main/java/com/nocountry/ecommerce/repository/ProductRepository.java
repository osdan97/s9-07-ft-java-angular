package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAllByCountry(String country, Pageable pageable);
    Page<Product> findAllByCategoryName(String category, Pageable pageable);
    Page<Product> findAllByCountryAndCategoryName(String country, String category, Pageable pageable);
    Optional<Product> findByName(String product);
}
