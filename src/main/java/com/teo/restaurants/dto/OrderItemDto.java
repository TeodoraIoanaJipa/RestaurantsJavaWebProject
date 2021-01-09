package com.teo.restaurants.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Integer productId;
    private Integer orderId;
    private Integer quantity;
}
