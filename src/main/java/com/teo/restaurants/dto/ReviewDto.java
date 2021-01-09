package com.teo.restaurants.dto;

import com.teo.restaurants.model.Review;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReviewDto {
    private Integer id;
    private Integer grade;
    private String comment;
    private Integer restaurantId;
    private Integer userId;
    private Integer orderId;
    private Date createdDate;

    public ReviewDto convertToDto(Review review){
        return ReviewDto.builder()
                .grade(review.getGrade())
                .comment(review.getComment())
                .restaurantId(review.getRestaurant().getId())
                .userId(review.getUser().getId())
                .orderId(review.getOrder().getOrderId())
                .createdDate(review.getCreatedDate())
                .build();
    }
}
