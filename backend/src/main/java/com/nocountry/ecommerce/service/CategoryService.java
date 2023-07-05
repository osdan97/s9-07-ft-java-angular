package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> list();
    Optional<Category> getOne(String id);
    Category findByName(String name);


    void save(Category category) ;

     void delete(String id);

     boolean existsById(String id) ;
}
