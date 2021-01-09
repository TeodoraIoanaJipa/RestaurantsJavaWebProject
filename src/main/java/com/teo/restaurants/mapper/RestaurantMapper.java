package com.teo.restaurants.mapper;


import com.teo.restaurants.model.Restaurant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet resultSet, int i) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(resultSet.getInt("id"));
        restaurant.setName(resultSet.getString("name"));
        restaurant.setDescription(resultSet.getString("description"));
        restaurant.setOpeningTime(resultSet.getString("opening_time"));
        restaurant.setClosingTime(resultSet.getString("closing_time"));
        restaurant.setPriceCategory(resultSet.getString("price_category"));
        restaurant.setType(resultSet.getString("type"));
        return restaurant;
    }
}
