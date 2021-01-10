package com.teo.restaurants.exception;

public class RestaurantTypeNotFound extends RuntimeException{
    public RestaurantTypeNotFound() {
        super("Restaurant type is not valid.");
    }
}
