package com.teo.restaurants.controller;

import com.teo.restaurants.model.OrderItem;

import com.teo.restaurants.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody List<OrderItem> orderItems, @RequestParam Integer restaurantId,
                            @RequestParam Integer userId) {
        orderService.save(orderItems, restaurantId, userId);
    }
}
