package com.teo.restaurants.controller;

import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/get-by-name")
    public ResponseEntity getRestaurantByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantByName(name));
        } catch (NoRestaurantFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-type")
    public ResponseEntity getRestaurantsByType(@RequestParam String type) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantsByType(type));
        } catch (NoRestaurantFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-price-category")
    public ResponseEntity getRestaurantsByPriceCategory(@RequestParam String priceCategory) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantsByPriceCategory(priceCategory));
        } catch (NoRestaurantFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Restaurant> getRestaurants() {
        return restaurantService.findAll();
    }

    @PostMapping("/add")
    public void addRestaurants(@RequestBody List<Restaurant> restaurants) {
        restaurantService.saveRestaurants(restaurants);
    }
}
