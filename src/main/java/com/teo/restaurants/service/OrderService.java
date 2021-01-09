package com.teo.restaurants.service;

import com.teo.restaurants.dto.CreateOrderDto;
import com.teo.restaurants.dto.OrderDto;
import com.teo.restaurants.dto.OrderItemDto;
import com.teo.restaurants.exception.FoodProductNotFound;
import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.*;
import com.teo.restaurants.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private Order getOrderRestaurantAndUser(Integer restaurantId, Integer userId){
        Order order = new Order();
        Optional<Restaurant> restaurant = restaurantsRepository.getRestaurantById(restaurantId);
        if (restaurant.isEmpty()) {
            throw new NoRestaurantFoundException("No restaurant found for id " + restaurantId);
        }
        Optional<User> user = userRepository.getUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        order.setRestaurant(restaurant.get());
        order.setUser(user.get());
        return order;
    }

    @Transactional
    public void save(CreateOrderDto createOrderDto) {
        Order order = getOrderRestaurantAndUser(createOrderDto.getRestaurantId(), createOrderDto.getUserId());
        Integer orderId = orderRepository.save(order.getRestaurant(), order.getUser());
        order.setOrderId(orderId);

        createOrderDto.getOrderItemDtos().forEach(orderItemDto -> {
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrder(order);
            Optional<FoodProduct> foodProduct = foodProductRepository.getFoodProductByIdAndRestaurantId(orderItemDto.getProductId(), order.getRestaurant().getId());
            if (foodProduct.isEmpty()) {
                throw new FoodProductNotFound();
            }
            orderItem1.setId(orderItemDto.getProductId());
            orderItem1.setProduct(foodProduct.get());
            orderItem1.setQuantity(orderItemDto.getQuantity());
            orderItemRepository.save(orderItem1);
        });

    }

    public List<Order> findAllOrdersByUserId(Integer userId) {
        List<Order> orders = new ArrayList<>();
        List<OrderDto> orderDtos = orderRepository.getOrderByUserId(userId);

        orderDtos.forEach(orderDto -> {
            Order order = getOrderRestaurantAndUser(orderDto.getRestaurantId(), orderDto.getUserId());
            order.setCreatedDate(orderDto.getCreatedDate());
            List<OrderItem> orderItems = new ArrayList<>();

            List<OrderItemDto> items = orderItemRepository.getOrderItemsByOrderId(orderDto.getOrderId());
            items.forEach(orderItemDto -> {
                Optional<FoodProduct> foodProduct = foodProductRepository
                        .getFoodProductByIdAndRestaurantId(orderItemDto.getProductId(), orderDto.getRestaurantId());
                if(foodProduct.isEmpty()){
                    throw new FoodProductNotFound();
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(orderItemDto.getQuantity());
                orderItem.setProduct(foodProduct.get());
                orderItems.add(orderItem);
            });
            order.setOrderItems(orderItems);
            orders.add(order);
        });
        return orders;
    }

}
