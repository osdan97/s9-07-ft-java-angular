package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.FavoritesDto;
import com.nocountry.ecommerce.model.Favorites;
import java.util.List;
import java.util.Optional;
public interface FavoritesService {
    List<Favorites> getFavoriteProducts(String customerId);
    Optional<Favorites> getOne(String id);
    void save(FavoritesDto favoritesDto);
    void deleteById(String id);
    void updateById(String id, FavoritesDto favoritesDto);

}
