package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.FavoritesDto;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Favorites;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.repository.FavoritesRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.service.FavoritesService;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    FavoritesRepository favoritesRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Favorites> getFavoriteProducts(String customerId) {
        List<Favorites> favorites = favoritesRepository.findAllByCustomersAccountUuid(customerId);
        return favorites;
    }

    @Override
    public Optional<Favorites> getOne(String id) {
        if(!favoritesRepository.existsById(id)){
            throw new IllegalStateException("Favorite does not exist");
        }
        Optional<Favorites> existedFavorite = favoritesRepository.findById(id);
        return (existedFavorite);
    }

    @Override
    public void save(FavoritesDto favoritesDto) {
        String customer_uuid = favoritesDto.getCustomers();
        Optional<Customers> customersExist = customerRepository.findById(customer_uuid);
        Customers customers = customersExist.get();
        String product_uuid = favoritesDto.getProduct();
        Optional<Product> productExist = productRepository.findById(product_uuid);
        Product product = productExist.get();
        Favorites favorites = new Favorites();
        favorites.setCustomers(customers);
        favorites.setProduct(product);
        favoritesRepository.save(favorites);
    }

    @Override
    public void deleteById(String id) {
        if(!favoritesRepository.existsById(id)){
            throw new IllegalStateException("Favorite does not exist");
        }
        favoritesRepository.deleteById(id);
    }

    @Override
    public void updateById(String id, FavoritesDto favoritesDto) {
        if(!favoritesRepository.existsById(id)){
            throw new IllegalStateException("Favorite does not exist");
        }
        Optional<Favorites> existedFavorite = favoritesRepository.findById(id);
        Favorites favoriteUpdated = existedFavorite.get();
        String customerId = favoritesDto.getCustomers();
        Optional<Customers> customersExist = customerRepository.findById(customerId);
        Customers customers = customersExist.get();
        favoriteUpdated.setCustomers(customers);
        String productId = favoritesDto.getProduct();
        Optional<Product> productExist = productRepository.findById(productId);
        Product product = productExist.get();
        favoriteUpdated.setProduct(product);

        favoritesRepository.save(favoriteUpdated);
    }
}
