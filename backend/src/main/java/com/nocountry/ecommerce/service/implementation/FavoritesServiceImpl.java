package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.FavoritesDto;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Favorites;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.repository.FavoritesRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.service.FavoritesService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (!favoritesRepository.existsById(id)) {
            throw new IllegalStateException("Favorite does not exist");
        }
        Optional<Favorites> existedFavorite = favoritesRepository.findById(id);
        return (existedFavorite);
    }

    @Override
    public Favorites save(FavoritesDto favoritesDto) {
        String customer_uuid = favoritesDto.getCustomers();
        if (!customerRepository.existsById(customer_uuid)) {
            throw new IllegalStateException("Customer does not exist");
        }
        Optional<Customers> customersExist = customerRepository.findById(customer_uuid);
        Customers customers = customersExist.get();

        String product_uuid = favoritesDto.getProduct();
        if (!productRepository.existsById(product_uuid)) {
            throw new IllegalStateException("Product does not exist");
        }
        Optional<Product> productExist = productRepository.findById(product_uuid);
        Product product = productExist.get();

        Favorites favorites = new Favorites();
        favorites.setId(UUID.randomUUID().toString());
        favorites.setCustomers(customers);
        favorites.setProduct(product);

        favoritesRepository.save(favorites);
        return favorites;
    }

    @Override
    public void deleteById(String id) {
        if (!favoritesRepository.existsById(id)) {
            throw new IllegalStateException("Favorite does not exist");
        }
        favoritesRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteByCustomerAndProduct(String accountId, String productId) {
        favoritesRepository.deleteFavoritesByCustomersAndProduct(accountId,productId);
    }

    @Override
    public void updateById(String id, FavoritesDto favoritesDto) {
        if(!favoritesRepository.existsById(id)){
            throw new IllegalStateException("Favorite does not exist");
        }

        Optional<Favorites> existedFavorite = favoritesRepository.findById(id);
        Favorites favoriteUpdated = existedFavorite.get();

        String customerId = favoritesDto.getCustomers();
        if (!customerRepository.existsById(customerId)){
            throw new IllegalStateException("Customer does not exist");
        }
        Optional<Customers> customersExist = customerRepository.findById(customerId);
        Customers customers = customersExist.get();
        favoriteUpdated.setCustomers(customers);

        String productId = favoritesDto.getProduct();
        if (!productRepository.existsById(productId)){
            throw new IllegalStateException("Product does not exist");
        }
        Optional<Product> productExist = productRepository.findById(productId);
        Product product = productExist.get();
        favoriteUpdated.setProduct(product);

        favoritesRepository.save(favoriteUpdated);
    }
}
