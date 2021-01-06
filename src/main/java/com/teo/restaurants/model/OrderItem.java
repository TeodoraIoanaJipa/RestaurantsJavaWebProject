package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Integer id;
    private FoodProduct product;
    private Order order;
    private Integer quantity;
}
