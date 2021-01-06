package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodProduct {
    private Integer id;
    private String name;
    private String description;
    private Double price;
}
