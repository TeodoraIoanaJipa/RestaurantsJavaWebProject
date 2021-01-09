package com.teo.restaurants.controller;

import com.teo.restaurants.exception.RestaurantNotFoundException;
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
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-type")
    public ResponseEntity getRestaurantsByType(@RequestParam String type) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantsByType(type));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-price-category")
    public ResponseEntity getRestaurantsByPriceCategory(@RequestParam String priceCategory) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantsByPriceCategory(priceCategory));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Restaurant> getRestaurants() {
        return restaurantService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurants(@RequestBody List<Restaurant> restaurants) {
        try {
            restaurantService.saveRestaurants(restaurants);
            return ResponseEntity.ok("Restaurants saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
