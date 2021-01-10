package com.teo.restaurants.dto;

import com.teo.restaurants.model.Review;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class ReviewDto {
    private Integer id;

    @Min(1)
    @Max(5)
    private Integer grade;
    @Length(max = 100)
    private String comment;
    @Min(1)
    @NotNull(message = "of the restaurant cannot be null.")
    private Integer restaurantId;
    @Min(1)
    @NotNull(message = "of the user cannot be null.")
    private Integer userId;
    @Min(1)
    @NotNull(message = "of the order cannot be null.")
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
