package com.teo.restaurants.service;

import com.teo.restaurants.dto.PriceCategory;
import com.teo.restaurants.dto.RestaurantType;
import com.teo.restaurants.exception.PriceCategoryInvalidException;
import com.teo.restaurants.exception.RestaurantNotFoundException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.repository.RestaurantsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        restaurant.setId(1);
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription("Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");
        restaurant.setOpeningTime("9:00");
        restaurant.setClosingTime("20:00");
        restaurant.setPriceCategory("ridicat");
        restaurant.setType("ceainarie");

        Restaurant restaurant2 = new Restaurant();
        restaurant2 .setId(2);
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
    @DisplayName("save restaurant valid")
    public void saveRestaurant()   {
        Restaurant restaurant = restaurants.get(0);
        restaurantService.saveRestaurant(restaurant);

        verify(restaurantsRepository).save(restaurant);
    }

    @Test
    @DisplayName("save restaurant when price category is invalid")
    public void saveRestaurantWithInvalidPriceCategory()   {
        Restaurant restaurant = restaurants.get(0);
        restaurant.setPriceCategory("OCATEGORIE");

        PriceCategoryInvalidException exception = assertThrows(PriceCategoryInvalidException.class,
                () -> restaurantService.saveRestaurant(restaurant));

        assertEquals("Price category is not valid.", exception.getMessage());
    }

    @Test
    @DisplayName("find restaurant by id when invalid id")
    void findRestaurantByIdFailure() {
        Integer restaurantId = 3;
        Optional<Restaurant> restaurant = Optional.empty();

        when(restaurantsRepository.findRestaurantById(restaurantId)).thenReturn(restaurant);

        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class,
                () -> restaurantService.findRestaurantById(restaurantId));

        assertEquals("Ooopsy! No restaurant with id " + restaurantId + " was found", exception.getMessage());
    }

    @Test
    @DisplayName("find restaurant by id success")
    void findRestaurantById() {
        Integer restaurantId = 2;
        Optional<Restaurant> restaurant = Optional.of(restaurants.get(1));

        when(restaurantsRepository.findRestaurantById(restaurantId)).thenReturn(restaurant);
        Restaurant actualRestaurant = restaurantService.findRestaurantById(restaurantId);

        verify(restaurantsRepository).findRestaurantById(restaurantId);
        assertEquals(actualRestaurant, restaurant.get());
    }

    @Test
    @DisplayName("find restaurant by name success")
    void findRestaurantByName() {
        String restaurantName = "Grano";
        Optional<Restaurant> restaurant = Optional.of(restaurants.get(1));

        when(restaurantsRepository.getRestaurantByName(restaurantName)).thenReturn(restaurant);
        Restaurant actualRestaurant = restaurantService.findRestaurantByName(restaurantName);

        verify(restaurantsRepository).getRestaurantByName(restaurantName);
        assertEquals(actualRestaurant, restaurant.get());
    }

    @Test
    @DisplayName("find restaurant by name failure")
    void findRestaurantByNameFailure() {
        String restaurantName = "New Pizza";
        Optional<Restaurant> restaurant = Optional.empty();

        when(restaurantsRepository.getRestaurantByName(restaurantName)).thenReturn(restaurant);
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class,
                () -> restaurantService.findRestaurantByName(restaurantName));

        assertEquals("Ooopsy! No restaurant with name " + restaurantName + " was found", exception.getMessage());
    }

    @Test
    @DisplayName("find restaurants by price category success")
    void findRestaurantsByPriceCategory() {
        String priceCategory = PriceCategory.MODERAT.name();
        List<Restaurant> expectedRestaurants = List.of(restaurants.get(1));

        when(restaurantsRepository.findAllByPriceCategory(priceCategory)).thenReturn(expectedRestaurants);
        List<Restaurant> actualRestaurant = restaurantService.findRestaurantsByPriceCategory(priceCategory);

        verify(restaurantsRepository).findAllByPriceCategory(priceCategory);
        assertEquals(actualRestaurant, expectedRestaurants);
    }

    @Test
    @DisplayName("find restaurants by price category success empty list")
    void findRestaurantsByPriceCategoryEmptyList() {
        String priceCategory = PriceCategory.PREMIUM.name();
        List<Restaurant> expectedRestaurants = new ArrayList<>();

        when(restaurantsRepository.findAllByPriceCategory(priceCategory)).thenReturn(expectedRestaurants);
        List<Restaurant> actualRestaurant = restaurantService.findRestaurantsByPriceCategory(priceCategory);

        verify(restaurantsRepository).findAllByPriceCategory(priceCategory);
        assertEquals(actualRestaurant, expectedRestaurants);
    }


    @Test
    @DisplayName("find restaurants by price category failure")
    void findRestaurantsByPriceCategoryFailure() {
        String priceCategory = "mic";

        PriceCategoryInvalidException exception = assertThrows(PriceCategoryInvalidException.class,
                () -> restaurantService.findRestaurantsByPriceCategory(priceCategory));

        assertEquals("Price category is not valid.", exception.getMessage());
    }

    @Test
    @DisplayName("find restaurants by type success")
    void findRestaurantsByType() {
        String type = RestaurantType.CEAINARIE.name();
        List<Restaurant> expectedRestaurants = List.of(restaurants.get(0));

        when(restaurantsRepository.findAllByType(type)).thenReturn(expectedRestaurants);
        List<Restaurant> actualRestaurant = restaurantService.findRestaurantsByType(type);

        verify(restaurantsRepository).findAllByType(type);
        assertEquals(actualRestaurant, expectedRestaurants);
    }

}
