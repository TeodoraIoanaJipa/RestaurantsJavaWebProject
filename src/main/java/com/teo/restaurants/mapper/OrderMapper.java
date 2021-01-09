package com.teo.restaurants.mapper;


import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;

import com.teo.restaurants.model.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setCreatedDate(resultSet.getDate("created_date"));

        Restaurant restaurant = new Restaurant();
        restaurant.setId(resultSet.getInt("restaurant_id"));
        order.setRestaurant(restaurant);

        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        order.setUser(user);
        return order;
    }
}
