package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.FavoritesDto;
import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Favorites;
import com.nocountry.ecommerce.service.FavoritesService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Favorites>> list(@PathVariable String customerId) {
        List<Favorites> list = favoritesService.getFavoriteProducts(customerId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FavoritesDto favoritesDto) {
       if (StringUtils.isBlank(favoritesDto.getCustomers()))
           return new ResponseEntity(new Mensaje("customer id is required"), HttpStatus.BAD_REQUEST);
       if (StringUtils.isBlank(favoritesDto.getProduct()))
           return new ResponseEntity(new Mensaje("product id is required"), HttpStatus.BAD_REQUEST);
       else {
           favoritesService.save(favoritesDto);
           return new ResponseEntity(favoritesService.save(favoritesDto), HttpStatus.OK);
       }
    }
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody FavoritesDto favoritesDto) {
        favoritesService.updateById(id, favoritesDto);
        return new ResponseEntity(new Mensaje("Favorite updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        if (favoritesService.getOne(id).isPresent()) {
            favoritesService.deleteById(id);
            return new ResponseEntity(new Mensaje("favorites deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("favorites does not exist"), HttpStatus.BAD_REQUEST);
    }
}
