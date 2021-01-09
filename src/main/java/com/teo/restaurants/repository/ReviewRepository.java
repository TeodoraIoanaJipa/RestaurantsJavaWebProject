package com.teo.restaurants.repository;

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

    private Review mapReview(Map<String, Object> map) {
        Review review = new Review();
        review.setId((Integer) map.get("id"));
        review.setGrade((Integer) map.get("grade"));
        review.setComment((String) map.get("comment"));
        review.setCreatedDate((Date) map.get("created_date"));
        return review;
    }

    public List<Review> findAll() {
        String sql = "SELECT * from reviews";
        try {
            List<Map<String, Object>> reviews = jdbcTemplate.queryForList(sql);
            List<Review> reviewsList = new ArrayList<>();
            for (Map<String, Object> map : reviews) {
                Review review = mapReview(map);
                reviewsList.add(review);
            }
            return reviewsList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
