package com.teo.restaurants.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderItemDto {
    @NotNull(message = "of the product id cannot be null.")
    @Min(1)
    private Integer productId;
    @NotNull(message = "for the quantity cannot be null.")
    @Min(1)
    @Max(20)
    private Integer quantity;
}
