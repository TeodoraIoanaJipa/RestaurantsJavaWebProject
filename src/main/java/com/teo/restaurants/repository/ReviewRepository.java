package com.teo.restaurants.repository;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Review review) {
        String sql = "INSERT into reviews VALUES(NULL, ?, ?, ?, ?, ?, ? )";
        jdbcTemplate.update(sql, review.getUser().getId(), review.getRestaurant().getId(),
                review.getOrder().getOrderId(), review.getGrade(), review.getComment(),
                review.getCreatedDate());
    }

    private ReviewDto mapReview(Map<String, Object> map) {
        return ReviewDto.builder()
                .id((Integer) map.get("id"))
                .grade((Integer) map.get("grade"))
                .comment((String) map.get("comment"))
                .restaurantId((Integer) map.get("restaurant_id"))
                .orderId((Integer) map.get("order_id"))
                .userId((Integer) map.get("user_id"))
                .createdDate((Date) map.get("created_date"))
                .build();
    }

    private List<ReviewDto> getReviews(String sql){
        try {
            List<Map<String, Object>> reviews = jdbcTemplate.queryForList(sql);
            List<ReviewDto> reviewsList = new ArrayList<>();
            for (Map<String, Object> map : reviews) {
                ReviewDto reviewDto = mapReview(map);
                reviewsList.add(reviewDto);
            }
            return reviewsList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<ReviewDto> findAll() {
        String sql = "SELECT * from reviews";
        return getReviews(sql);
    }

    public List<ReviewDto> findAllByRestaurantId(Integer restaurantId) {
        String sql = "SELECT * from reviews where restaurant_id = "+ restaurantId;
        return getReviews(sql);
    }

    public List<ReviewDto> findAllByUserId(Integer userId) {
        String sql = "SELECT * from reviews where user_id = "+ userId;
        return getReviews(sql);
    }
}
