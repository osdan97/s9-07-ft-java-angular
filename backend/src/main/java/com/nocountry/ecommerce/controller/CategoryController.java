package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Category;
import com.nocountry.ecommerce.service.CategoryService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<Category>> list() {
        List<Category> list = categoryService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Category categoriaEnt) {


        if (StringUtils.isBlank(categoriaEnt.getName()))
            return new ResponseEntity(new Mensaje("the name can't be empty"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(categoriaEnt.getDescription()))
            return new ResponseEntity(new Mensaje("the description can't be empty"), HttpStatus.BAD_REQUEST);
        if (categoriaEnt.getState() == null )
            return new ResponseEntity<>("State can't be empty", HttpStatus.BAD_REQUEST);
        else{
            Category categoria = new Category( categoriaEnt.getName(),categoriaEnt.getDescription(),categoriaEnt.getNumber(),categoriaEnt.getState());
            categoryService.save(categoria);
            return new ResponseEntity(new Mensaje("category created successfully"), HttpStatus.OK);
        }


    }

    @PutMapping("/updatebyname/{name}")
    public ResponseEntity<?> updateByName(@PathVariable("name") String name, @RequestBody Category categoriaEnt) {
        if (categoryService.findByName(name)==null)
            return new ResponseEntity(new Mensaje(" category does not exist"), HttpStatus.NOT_FOUND);


        Category category = categoryService.findByName(name);
        category.setName(categoriaEnt.getName());
        category.setDescription(categoriaEnt.getDescription());
        category.setNumber(categoriaEnt.getNumber());
        category.setState(categoriaEnt.getState());
        categoryService.save(category);
        return new ResponseEntity(new Mensaje("category updated successfully"), HttpStatus.OK);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Category categoriaEnt) {
        if (!categoryService.existsById(id))
            return new ResponseEntity(new Mensaje(" category does not exist"), HttpStatus.NOT_FOUND);


        Category category = categoryService.getOne(id).get();
        category.setName(categoriaEnt.getName());
        category.setDescription(categoriaEnt.getDescription());
        category.setNumber(categoriaEnt.getNumber());
        category.setState(categoriaEnt.getState());
        categoryService.save(category);
        return new ResponseEntity(new Mensaje("category updated successfully"), HttpStatus.OK);
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (!categoryService.existsById(id))
            return new ResponseEntity(new Mensaje("category does not exist"), HttpStatus.NOT_FOUND);
        categoryService.delete(id);
        return new ResponseEntity(new Mensaje("category deleted successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/deletebyname/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {
        if (categoryService.findByName(name)==null)
            return new ResponseEntity(new Mensaje("category does not exist"), HttpStatus.NOT_FOUND);
        String id=categoryService.findByName(name).getId();
        categoryService.delete(id);
        return new ResponseEntity(new Mensaje("category deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/detail/{name}")
    public ResponseEntity<Category> getById(@PathVariable("name") String name) {
        if (categoryService.findByName(name)==null)
            return new ResponseEntity(new Mensaje("category does not exist"), HttpStatus.NOT_FOUND);
        Category category = categoryService.findByName(name);
        return new ResponseEntity(category, HttpStatus.OK);
    }
}
