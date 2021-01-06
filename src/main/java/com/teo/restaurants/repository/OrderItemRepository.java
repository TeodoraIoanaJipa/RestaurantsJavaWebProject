package com.teo.restaurants.repository;

import com.teo.restaurants.model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(OrderItem orderItem) {
        String sql = "INSERT into food_orders VALUES(NULL, ?, ?, ?)";
        jdbcTemplate.update(sql, orderItem.getProduct().getId(), orderItem.getQuantity(),
                orderItem.getOrder().getOrderId());
    }
}
