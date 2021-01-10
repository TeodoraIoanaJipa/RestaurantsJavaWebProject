package com.teo.restaurants.exception;

public class PriceCategoryInvalidException extends RuntimeException{
    public PriceCategoryInvalidException() {
        super("Price category is not valid.");
    }
}
