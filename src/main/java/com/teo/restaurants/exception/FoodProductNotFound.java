package com.teo.restaurants.exception;

public class FoodProductNotFound extends RuntimeException{
    public FoodProductNotFound() {
        super("Food Product was not found.");
    }
}
