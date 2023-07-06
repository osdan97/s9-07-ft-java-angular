package com.nocountry.ecommerce.repository;

import com.nocountry.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {

    @Query("SELECT i FROM Inventory i WHERE i.name = :name")
    Inventory getByName(@Param("name") String name);
}
