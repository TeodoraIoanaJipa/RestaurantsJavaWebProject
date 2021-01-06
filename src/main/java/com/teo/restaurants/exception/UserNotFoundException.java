package com.teo.restaurants.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("No user with that id was found.");
    }
}
