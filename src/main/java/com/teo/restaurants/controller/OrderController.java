package com.teo.restaurants.controller;

import com.teo.restaurants.dto.CreateOrderDto;
import com.teo.restaurants.exception.RestaurantNotFoundException;
import com.teo.restaurants.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@Valid @RequestBody CreateOrderDto createOrderDto) {
        try {
            orderService.save(createOrderDto);
            return ResponseEntity.ok("Order created successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-by-user")
    public ResponseEntity getOrdersByUserId(@RequestParam Integer userId) {
        try {
            return ResponseEntity.ok(orderService.findAllOrdersByUserId(userId));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
