package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String>{

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category getByName(@Param("name") String name );

}
