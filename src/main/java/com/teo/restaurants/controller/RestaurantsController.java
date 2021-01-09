package com.teo.restaurants.controller;

import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/get-by-name")
    public Restaurant getRestaurantByName(@RequestParam String name) {
        return restaurantService.findRestaurantByName(name);
    }

    @GetMapping("/get-by-type")
    public List<Restaurant> getRestaurantsByType(@RequestParam String type) {
        return restaurantService.findRestaurantsByType(type);
    }

    @GetMapping("/get-by-price-category")
    public List<Restaurant> getRestaurantsByPriceCategory(@RequestParam String priceCategory) {
        return restaurantService.findRestaurantsByPriceCategory(priceCategory);
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
