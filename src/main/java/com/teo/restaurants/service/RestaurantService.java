package com.teo.restaurants.service;

import com.teo.restaurants.dto.PriceCategory;
import com.teo.restaurants.dto.RestaurantType;
import com.teo.restaurants.exception.PriceCategoryInvalidException;
import com.teo.restaurants.exception.RestaurantNotFoundException;
import com.teo.restaurants.exception.RestaurantTypeNotFound;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.repository.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Transactional
    public void saveRestaurant(Restaurant restaurant) {
        String priceCategory = restaurant.getPriceCategory().toUpperCase();
        if (priceCategory.equals(PriceCategory.ACCESIBIL.name()) || priceCategory.equals(PriceCategory.MODERAT.name())
                || priceCategory.equals(PriceCategory.RIDICAT.name()) || priceCategory.equals(PriceCategory.PREMIUM.name())) {
            restaurantsRepository.save(restaurant);
        } else {
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
        List<String> restaurantTypes = List.of(RestaurantType.values())
                .stream()
                .map(restaurantType -> restaurantType.name())
                .collect(Collectors.toList());
        if (restaurantTypes.contains(type.toUpperCase())) {
            return restaurantsRepository.findAllByType(type);
        }
        throw new RestaurantTypeNotFound();
    }

    public List<Restaurant> findRestaurantsByPriceCategory(String priceCategory) {
        List<String> priceCategories = List.of(PriceCategory.MODERAT.name(),
                PriceCategory.ACCESIBIL.name(), PriceCategory.PREMIUM.name(),
                PriceCategory.RIDICAT.name());
        if (priceCategories.contains(priceCategory.toUpperCase())) {
            return restaurantsRepository.findAllByPriceCategory(priceCategory);
        }
        throw new PriceCategoryInvalidException();
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
