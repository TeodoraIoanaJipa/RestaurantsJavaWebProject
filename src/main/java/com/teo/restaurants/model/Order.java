package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Integer orderId;
    private Date createdDate;
    private Restaurant restaurant;
    private User user;
    private List<OrderItem> orderItems;
}
