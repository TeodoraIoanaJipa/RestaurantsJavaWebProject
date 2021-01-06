package com.teo.restaurants.exception;

public class NoRestaurantFoundException extends RuntimeException{
    public NoRestaurantFoundException(String message) {
        super(message);
    }
}
