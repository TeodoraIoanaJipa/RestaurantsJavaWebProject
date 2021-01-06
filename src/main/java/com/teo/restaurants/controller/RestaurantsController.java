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

    @GetMapping("/all")
    public List<Restaurant> getRestaurants() {
        return restaurantService.findAll();
    }

    @PostMapping("/add")
    public void addRestaurants(@RequestBody List<Restaurant> restaurants) {
        restaurantService.saveRestaurants(restaurants);
    }
}
