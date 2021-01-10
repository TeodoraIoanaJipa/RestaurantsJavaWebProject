package com.teo.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {
    private Integer id;
    @NotNull
    @Length(min = 5, max= 20)
    private String firstName;
    @NotNull
    @Length(min = 5, max= 20)
    private String lastName;
    @NotNull
    @Length(min = 5, max= 15)
    private String username;
    @NotNull
    @Length(min = 5, max= 45)
    @Email
    private String email;
    @JsonIgnore
    @NotNull
    @Length(min = 5, max= 20)
    private String password;
}
