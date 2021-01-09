package com.teo.restaurants.dto;

import com.teo.restaurants.model.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private Integer grade;
    private String comment;
    private Integer restaurantId;
    private Integer userId;
    private Integer orderId;

    public ReviewDto convertToDto(Review review){
        return ReviewDto.builder()
                .grade(review.getGrade())
                .comment(review.getComment())
                .restaurantId(review.getRestaurant().getId())
                .userId(review.getUser().getId())
                .orderId(review.getOrder().getOrderId())
                .build();
    }
}
