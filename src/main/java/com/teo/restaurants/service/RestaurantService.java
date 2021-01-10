package com.teo.restaurants.service;

import com.teo.restaurants.dto.PriceCategory;
import com.teo.restaurants.exception.PriceCategoryInvalidException;
import com.teo.restaurants.exception.RestaurantNotFoundException;
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
    public void saveRestaurant(Restaurant restaurant) {
        String priceCategory = restaurant.getPriceCategory().toUpperCase();
        if(priceCategory.equals(PriceCategory.ACCESIBIL.name()) || priceCategory.equals(PriceCategory.MODERAT.name())
        || priceCategory.equals(PriceCategory.RIDICAT.name()) || priceCategory.equals(PriceCategory.PREMIUM.name())){
            restaurantsRepository.save(restaurant);
        }else{
            throw new PriceCategoryInvalidException();
        }
    }

    public List<Restaurant> findAll() {
        return restaurantsRepository.findAll();
    }

    public Restaurant findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantsRepository.findRestaurantById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantNotFoundException("Ooopsy! No restaurant with id " + id + " was found");
        }
    }

    public List<Restaurant> findRestaurantsByType(String type) {
        return restaurantsRepository.findAllByType(type);
    }

    public List<Restaurant> findRestaurantsByPriceCategory(String priceCategory) {
        if(priceCategory.toUpperCase().equals(PriceCategory.MODERAT.name()) ||
                priceCategory.toUpperCase().equals(PriceCategory.ACCESIBIL.name()) ||
                priceCategory.toUpperCase().equals(PriceCategory.PREMIUM.name()) ||
                priceCategory.toUpperCase().equals(PriceCategory.RIDICAT.name())){
            return restaurantsRepository.findAllByPriceCategory(priceCategory);
        }
        throw new RestaurantNotFoundException("Ooopsy! No restaurants with that price category were found");
    }

    public Restaurant findRestaurantByName(String name) {
        Optional<Restaurant> restaurant = restaurantsRepository.getRestaurantByName(name);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantNotFoundException("Ooopsy! No restaurant with name " + name + " was found");
        }
    }
}
