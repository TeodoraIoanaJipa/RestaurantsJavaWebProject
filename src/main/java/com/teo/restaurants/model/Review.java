package com.teo.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Order order;
    private Date createdDate;
}
