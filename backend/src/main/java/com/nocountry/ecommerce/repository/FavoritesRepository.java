package com.nocountry.ecommerce.repository;


import com.nocountry.ecommerce.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites,String> {
    List<Favorites> findAllByCustomersAccountUuid(String customerId);
}

