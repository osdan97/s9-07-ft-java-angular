package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Inventory;
import com.nocountry.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product getByName(@Param("name") String name);
    Page<Product> findAllByCountry(String country, Pageable pageable);
    Page<Product> findAllByCategoryName(String category, Pageable pageable);
    Page<Product> findAllByCountryAndCategoryName(String country, String category, Pageable pageable);
    Optional<Product> findByName(String product);
    @Modifying
    @Query(value = "UPDATE PRODUCT SET stock=:stock, state=:state WHERE product_uuid=:product_uuid",
            nativeQuery = true)
    void updateStock(@Param("stock") int stock, @Param("state") String state, @Param("product_uuid") String productUuid);
}
