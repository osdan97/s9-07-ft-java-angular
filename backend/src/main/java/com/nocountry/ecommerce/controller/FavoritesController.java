package com.nocountry.ecommerce.controller;

import com.nocountry.ecommerce.dto.FavoritesDto;
import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Favorites;
import com.nocountry.ecommerce.service.FavoritesService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get all favorites", responses = {
            @ApiResponse(responseCode = "200", description = "Favorites list",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "404", description = "Favorites not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Favorites>> list(@PathVariable String customerId) {
        List<Favorites> list = favoritesService.getFavoriteProducts(customerId);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @Operation(summary = "Add a new favorite",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Favorite created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Favorite already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FavoritesDto favoritesDto) {
       if (StringUtils.isBlank(favoritesDto.getCustomers()))
           return new ResponseEntity(new Mensaje("customer id is required"), HttpStatus.BAD_REQUEST);
       if (StringUtils.isBlank(favoritesDto.getProduct()))
           return new ResponseEntity(new Mensaje("product id is required"), HttpStatus.BAD_REQUEST);
       else {
           return new ResponseEntity(favoritesService.save(favoritesDto), HttpStatus.OK);
       }
    }


    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody FavoritesDto favoritesDto) {
        favoritesService.updateById(id, favoritesDto);
        return new ResponseEntity(new Mensaje("Favorite updated"), HttpStatus.OK);
    }
    @Operation(summary = "Delete a favorite",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Favorite deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Favorite not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            })
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        if (favoritesService.getOne(id).isPresent()) {
            favoritesService.deleteById(id);
            return new ResponseEntity(new Mensaje("favorites deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("favorites does not exist"), HttpStatus.BAD_REQUEST);
    }
}
