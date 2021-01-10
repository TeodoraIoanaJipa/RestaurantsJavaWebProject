package com.teo.restaurants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teo.restaurants.exception.RestaurantNotFoundException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestaurantsController.class)
class RestaurantsControllerTest {

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private MockMvc mockMvc;

    private Restaurant restaurant;

    @BeforeEach
    private void setupRestaurant() {
        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription("Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");
        restaurant.setOpeningTime("9:00");
        restaurant.setClosingTime("20:00");
        restaurant.setPriceCategory("ridicat");
        restaurant.setType("ceainarie");
    }

    @Test
    @DisplayName("Get a restaurant by id valid")
    void getRestaurantById() throws Exception {
        Mockito.when(restaurantService.findRestaurantById(1)).thenReturn(restaurant);
        String url = "/restaurants/get-by-id" + "?id=1";
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(restaurant.getName()));
    }

    @Test
    @DisplayName("Get a restaurant by id non existent")
    void getRestaurantByIdFailure() throws Exception {
        String url = "/restaurants/get-by-id" + "?id=1";

        Mockito.when(restaurantService.findRestaurantById(1)).thenThrow(new RestaurantNotFoundException("Ooopsy! No restaurant with id 1 was found"));

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}