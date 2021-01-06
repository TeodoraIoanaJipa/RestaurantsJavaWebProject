package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Review {
    private Integer id;
    private Integer grade;
    private String comment;
    private Restaurant restaurant;
    private User user;
    private Order order;
    private Date createdDate;
}
