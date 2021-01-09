package com.teo.restaurants.repository;

import com.teo.restaurants.dto.CreateOrderDto;
import com.teo.restaurants.dto.OrderDto;
import com.teo.restaurants.mapper.OrderMapper;
import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Restaurant restaurant, User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT into orders VALUES(NULL, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"order_id"});
            ps.setInt(1, restaurant.getId());
            ps.setInt(2, user.getId());
            ps.setDate(3, new java.sql.Date(new Date().getYear(), new Date().getMonth(), new Date().getDay()));
            return ps;
        }, keyHolder);

        return (int) keyHolder.getKey().intValue();
    }

    public List<OrderDto> getOrderByUserId(Integer userId) {
        String sql = "SELECT * from orders WHERE user_id = " + userId;
        try {
            List<Map<String, Object>> orders = jdbcTemplate.queryForList(sql);
            List<OrderDto> ordersList = new ArrayList<>();
            for (Map<String, Object> map : orders) {
                OrderDto order = OrderDto.builder()
                        .orderId((Integer) map.get("order_id"))
                        .restaurantId((Integer) map.get("restaurant_id"))
                        .userId((Integer) map.get("user_id"))
                        .createdDate((Date) map.get("created_date"))
                        .build();
                ordersList.add(order);
            }
            return ordersList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Optional<Order> getOrderByOrderId(Integer orderId) {
        String sql = "SELECT * from orders WHERE order_id = ?";
        try {
            Order order = jdbcTemplate.queryForObject(sql, new OrderMapper(), orderId);
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
