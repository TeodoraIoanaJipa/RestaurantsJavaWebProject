package com.teo.restaurants.service;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.RestaurantsRepository;
import com.teo.restaurants.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantsRepository restaurantsRepository;

    @Test
    @DisplayName("save review")
    void save() {
        ReviewDto reviewDto = ReviewDto.builder()
                .comment("Mancare foarte buna.")
                .grade(5)
                .restaurantId(1)
                .userId(1)
                .orderId(1)
                .build();

        Review review = new Review();
        review.setComment("Mancare foarte buna.");
        review.setGrade(5);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("Ceainaria Infinitea");
        restaurant.setDescription("Te vei bucura de aromele ceaiurilor Infinitea, o paletă colorată de dulcețuri, sucuri de fructe proaspete");

        User user = new User();
        user.setId(1);

        Order order = new Order();
        order.setOrderId(1);

        review.setRestaurant(restaurant);
        review.setUser(user);
        review.setOrder(order);

        when(reviewService.convertFromDto(reviewDto)).thenReturn(review);
        when(restaurantsRepository.findRestaurantById(restaurant.getId())).thenReturn(Optional.of(restaurant));

        reviewService.save(reviewDto);

        Mockito.verify(reviewRepository).save(review);
    }
}