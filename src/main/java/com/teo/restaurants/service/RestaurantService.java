package com.teo.restaurants.service;

import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.repository.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Restaurant> findRestaurantsByType(String type) {
        return restaurantsRepository.findAllByType(type);
    }

    public List<Restaurant> findRestaurantsByPriceCategory(String priceCategory) {
        return restaurantsRepository.findAllByPriceCategory(priceCategory);
    }

    public Restaurant findRestaurantByName(String name) {
        Optional<Restaurant> restaurant = restaurantsRepository.getRestaurantByName(name);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new NoRestaurantFoundException("No restaurant with name " + name + " was found");
        }
    }
}
