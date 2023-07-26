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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
@SecurityRequirement(name = "jwt")
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
        try {
            if (StringUtils.isBlank(favoritesDto.getCustomers()))
                return new ResponseEntity(new Mensaje("customer id is required"), HttpStatus.BAD_REQUEST);
            if (StringUtils.isBlank(favoritesDto.getProduct()))
                return new ResponseEntity(new Mensaje("product id is required"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity(favoritesService.save(favoritesDto), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("error occurred" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        try {
            if (favoritesService.getOne(id).isPresent()) {
                favoritesService.deleteById(id);
                return new ResponseEntity(new Mensaje("favorites deleted successfully"), HttpStatus.OK);
            }
            return new ResponseEntity(new Mensaje("favorites does not exist"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("error occurred") + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/deletebyproduct/{account_uuid}/{product_uuid}")
    public ResponseEntity<?> deleteByCustomerAndProduct(@PathVariable("account_uuid") String account_uuid, @PathVariable("product_uuid") String product_uuid) {
        try {
                favoritesService.deleteByCustomerAndProduct(account_uuid,product_uuid);
                return new ResponseEntity(new Mensaje("favorites deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("error occurred") + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @CrossOrigin(origins = "https://s9-07-ft-java-angular-git-front-develop-nocountry-c11-16.vercel.app")
    @DeleteMapping("/deletefavorite")
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoritesDto favoritesDto) {
        try {
            String account_uuid = favoritesDto.getCustomers();
            String product_uuid = favoritesDto.getProduct();
            favoritesService.deleteByCustomerAndProduct(account_uuid,product_uuid);
            return new ResponseEntity(new Mensaje("favorites deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("error occurred") + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

