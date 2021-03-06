package com.teo.restaurants.controller;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.exception.RestaurantNotFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@Valid @RequestBody ReviewDto reviewDto) {
        try {
            reviewService.save(reviewDto);
            return ResponseEntity.ok("Review added successfully.");
        } catch (RestaurantNotFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/get-by-restaurant-id")
    public List<Review> getAllReviewsByRestaurantId(@RequestParam Integer restaurantId) {
        return reviewService.findAllByRestaurantId(restaurantId);
    }

    @GetMapping("/get-by-user-id")
    public List<Review> getAllReviewsByUserId(@RequestParam Integer userId) {
        return reviewService.findAllByUserId(userId);
    }

}
