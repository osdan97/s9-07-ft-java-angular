package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.repository.AccountRepository;
import com.nocountry.ecommerce.repository.CategoryRepository;
import com.nocountry.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicelmpl implements CategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getOne(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.getByName(name);
    }





    @Override
    public void save(Category category) {


        categoryRepository.save(category);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return categoryRepository.existsById(id);
    }


}
