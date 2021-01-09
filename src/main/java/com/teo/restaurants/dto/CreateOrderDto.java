package com.teo.restaurants.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOrderDto {
    private Integer restaurantId;
    private Integer userId;
    private List<CreateOrderItemDto> orderItemDtos;
}
