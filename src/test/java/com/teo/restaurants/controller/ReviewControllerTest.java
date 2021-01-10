package com.teo.restaurants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.model.User;
import com.teo.restaurants.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
class ReviewControllerTest {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    private ReviewDto reviewDto;

    private Review review;

    @BeforeEach
    private void setupReview() {
        reviewDto = ReviewDto.builder()
                .comment("Mancare foarte buna.")
                .grade(5)
                .restaurantId(1)
                .userId(1)
                .orderId(1)
                .build();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription("Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");

        User user = new User();
        user.setId(1);

        Order order = new Order();
        order.setOrderId(1);
        order.setRestaurant(restaurant);

        review = new Review();
        review.setComment("Mancare foarte buna.");
        review.setGrade(5);
        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setOrder(order);
        review.setCreatedDate(new Date());
    }

    @Test
    void addReview() throws Exception {
        Mockito.when(reviewService.convertFromDto(reviewDto)).thenReturn(review);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/review/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isOk());
    }
}