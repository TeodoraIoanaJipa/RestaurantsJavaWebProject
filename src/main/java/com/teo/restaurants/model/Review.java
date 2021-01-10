package com.teo.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Review {
    private Integer id;
    private Integer grade;
    private String comment;
    private Restaurant restaurant;
    private User user;
    @JsonIgnore
    private Order order;
    private Date createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(getGrade(), review.getGrade()) &&
                Objects.equals(getComment(), review.getComment()) &&
                Objects.equals(getRestaurant(), review.getRestaurant()) &&
                Objects.equals(getUser(), review.getUser()) &&
                Objects.equals(getOrder(), review.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGrade(), getComment(), getRestaurant(), getUser(), getOrder(), getCreatedDate());
    }
}
