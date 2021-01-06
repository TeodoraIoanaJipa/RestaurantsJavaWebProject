package com.teo.restaurants.service;

import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.OrderItem;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.User;
import com.teo.restaurants.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private FoodProductRepository foodProductRepository;
    @Autowired
    private RestaurantsRepository restaurantsRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(List<OrderItem> orderItemList, Integer restaurantId, Integer userId) {
        Optional<Restaurant> chosenRestaurant = restaurantsRepository.getRestaurantById(restaurantId);
        Optional<User> user = userRepository.getUserById(userId);
        if (chosenRestaurant.isPresent()) {
            if (user.isPresent()) {
                Integer orderId = orderRepository.save(chosenRestaurant.get(), user.get());
                Order order = new Order();
                order.setOrderId(orderId);
                orderItemList.forEach(orderItem -> {
                    foodProductRepository.getFoodProductByIdAndRestaurantId(orderItem.getId(), chosenRestaurant.get().getId());
                    orderItem.setOrder(order);
                    orderItemRepository.save(orderItem);
                });
            } else {
                throw new UserNotFoundException();
            }
        } else {
            throw new NoRestaurantFoundException("No restaurant found for id " + restaurantId);
        }

    }
}
