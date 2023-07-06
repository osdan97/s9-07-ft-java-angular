package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product getByName(@Param("name") String name);
    Page<Product> findAllByCountry(String country, Pageable pageable);
    Page<Product> findAllByCategoryName(String category, Pageable pageable);
    Page<Product> findAllByCountryAndCategoryName(String country, String category, Pageable pageable);

}
