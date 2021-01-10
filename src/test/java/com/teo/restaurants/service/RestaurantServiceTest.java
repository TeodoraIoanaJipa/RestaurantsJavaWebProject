package com.teo.restaurants.service;

import com.teo.restaurants.exception.PriceCategoryInvalidException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.repository.RestaurantsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantsRepository restaurantsRepository;

    private List<Restaurant> restaurants;

    @BeforeEach
    private void setupRestaurants()   {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription(" Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");
        restaurant.setOpeningTime("9:00");
        restaurant.setClosingTime("20:00");
        restaurant.setPriceCategory("moderat");
        restaurant.setType("ceainarie");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Grano");
        restaurant2.setDescription("Pasiunea pentru bucătăria italiană este dusă la alt nivel, iar selecția de vinuri care să completeze experiența gastronomică este pe măsură.");
        restaurant2.setOpeningTime("9:00");
        restaurant2.setClosingTime("20:00");
        restaurant2.setPriceCategory("moderat");
        restaurant2.setType("restaurant");

        restaurants = new ArrayList<>();
        restaurants.add(restaurant);
        restaurants.add(restaurant2);
    }

    @Test
    public void saveRestaurantWithInvalidPriceCategory()   {
        Restaurant restaurant = restaurants.get(0);
        restaurant.setPriceCategory("OCATEGORIE");

        Restaurant finalRestaurant = restaurant;
        PriceCategoryInvalidException exception = assertThrows(PriceCategoryInvalidException.class,
                () -> restaurantService.saveRestaurant(finalRestaurant));

        assertEquals("Price category is not valid.", exception.getMessage());
    }

    @Test
    public void saveRestaurantWithValidPriceCategory()   {
        Restaurant restaurant = restaurants.get(0);

        restaurantService.saveRestaurant(restaurant);

        verify(restaurantsRepository).save(restaurant);
    }

}
