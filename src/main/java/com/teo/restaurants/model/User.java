package com.teo.restaurants.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
