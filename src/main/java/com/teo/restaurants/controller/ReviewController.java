package com.teo.restaurants.controller;

import com.teo.restaurants.dto.ReviewDto;
import com.teo.restaurants.exception.NoRestaurantFoundException;
import com.teo.restaurants.exception.UserNotFoundException;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.Review;
import com.teo.restaurants.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody ReviewDto reviewDto) {
        try {
            reviewService.save(reviewDto);
            return ResponseEntity.ok("Review added.");
        } catch (NoRestaurantFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

}
