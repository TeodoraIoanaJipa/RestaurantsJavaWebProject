package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
    private Integer id;
    private String name;
    private String description;
    private String openingTime;
    private String closingTime;
    private String type;
    private String priceCategory;
}
