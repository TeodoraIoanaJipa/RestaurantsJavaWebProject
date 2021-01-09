package com.teo.restaurants.service;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.exception.OrderNotFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.OrderRepository;
import com.teo.restaurants.repository.RestaurantsRepository;
import com.teo.restaurants.repository.ReviewRepository;
import com.teo.restaurants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void save(ReviewDto reviewDto) {
        Optional<Restaurant> restaurant = restaurantsRepository.getRestaurantById(reviewDto.getRestaurantId());
        if (restaurant.isEmpty()) {
            throw new NoRestaurantFoundException("No restaurant was found");
        }

        Optional<User> user = userRepository.getUserById(reviewDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Optional<Order> order = orderRepository.getOrderByOrderId(reviewDto.getOrderId());
        if (order.isEmpty()) {
            throw new OrderNotFoundException();
        }

        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setGrade(reviewDto.getGrade());
        review.setUser(user.get());
        review.setRestaurant(restaurant.get());
        review.setOrder(order.get());
        review.setCreatedDate(new Date());

        reviewRepository.save(review);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}