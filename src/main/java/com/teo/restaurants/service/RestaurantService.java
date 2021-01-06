package com.teo.restaurants.service;

import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.repository.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Transactional
    public void saveRestaurants(List<Restaurant> restaurantList) {
        restaurantList.forEach(restaurant -> restaurantsRepository.save(restaurant));
    }

    public List<Restaurant> findAll() {
        return restaurantsRepository.findAll();
    }
}
