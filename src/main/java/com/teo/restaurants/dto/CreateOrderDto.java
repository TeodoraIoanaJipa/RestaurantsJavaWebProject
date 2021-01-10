package com.teo.restaurants.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CreateOrderDto {
    @NotNull(message = "of the restaurant cannot be null")
    @Min(1)
    private Integer restaurantId;
    @NotNull(message = "of the user cannot be null")
    @Min(1)
    private Integer userId;
    @Valid
    private List<CreateOrderItemDto> orderItemDtos;
}
