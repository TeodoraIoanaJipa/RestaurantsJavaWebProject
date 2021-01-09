package com.teo.restaurants.controller;

import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String restaurantsPage(Model model) {
        List<Restaurant> restaurantList = restaurantService.findAll();
        model.addAttribute("restaurants", restaurantList);
        return "restaurants.html";
    }
}
