package com.teo.restaurants.dto;

import lombok.Data;

@Data
public class CreateOrderItemDto {
    private Integer productId;
    private Integer quantity;
}
