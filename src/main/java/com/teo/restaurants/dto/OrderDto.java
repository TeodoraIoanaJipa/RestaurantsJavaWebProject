package com.teo.restaurants.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Integer orderId;
    private Integer restaurantId;
    private Integer userId;
    private Date createdDate;
    private List<CreateOrderItemDto> createOrderItemDtos;
}
