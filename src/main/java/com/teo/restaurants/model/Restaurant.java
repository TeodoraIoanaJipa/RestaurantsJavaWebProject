package com.teo.restaurants.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class Restaurant {
    private Integer id;
    @NotNull
    @NotBlank(message = " of the name cannot be empty. ")
    @Length(max = 45)
    private String name;
    @NotNull
    @Length(max = 250)
    private String description;
    @NotNull
    @Length(min = 4, max= 5)
    private String openingTime;
    @NotNull
    @Length(min = 4, max= 5)
    private String closingTime;
    @NotNull
    @Length(min = 5, max= 45)
    private String type;
    @NotNull
    @Length(min = 5, max= 45)
    private String priceCategory;
}
