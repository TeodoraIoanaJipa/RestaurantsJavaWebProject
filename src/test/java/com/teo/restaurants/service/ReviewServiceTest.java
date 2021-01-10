package com.teo.restaurants.service;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.OrderRepository;
import com.teo.restaurants.repository.RestaurantsRepository;
import com.teo.restaurants.repository.ReviewRepository;
import com.teo.restaurants.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantsRepository restaurantsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    private ReviewDto reviewDto;

    private Restaurant restaurant;

    private User user;

    private Order order;

    @BeforeEach
    private void setupReview() {
        reviewDto = ReviewDto.builder()
                .comment("Mancare foarte buna.")
                .grade(5)
                .restaurantId(1)
                .userId(1)
                .orderId(1)
                .build();

        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription("Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");

        user = new User();
        user.setId(1);

        order = new Order();
        order.setOrderId(1);
        order.setRestaurant(restaurant);
    }

    @Test
    @DisplayName("save review")
    void save() {
        Review review = new Review();
        review.setComment("Mancare foarte buna.");
        review.setGrade(5);
        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setOrder(order);
        review.setCreatedDate(new Date());

        convertFromDto();
        reviewService.save(reviewDto);

        Mockito.verify(reviewRepository).save(review);
    }

    @Test
    @DisplayName("convert review from dto")
    void convertFromDto() {
        Review review = new Review();
        review.setComment("Mancare foarte buna.");
        review.setGrade(5);
        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setOrder(order);
        review.setCreatedDate(new Date());

        when(restaurantsRepository.findRestaurantById(restaurant.getId())).thenReturn(Optional.of(restaurant));
        when(userRepository.getUserById(user.getId())).thenReturn(Optional.of(user));
        when(orderRepository.getOrderByOrderId(order.getOrderId())).thenReturn(Optional.of(order));

        Review actualReview = reviewService.convertFromDto(reviewDto);
        assertEquals(actualReview, review);
    }
}