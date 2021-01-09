package com.teo.restaurants.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("No order was found.");
    }
}
