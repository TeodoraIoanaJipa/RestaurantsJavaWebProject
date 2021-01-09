package com.teo.restaurants.repository;

import com.teo.restaurants.dto.OrderItemDto;
import com.teo.restaurants.model.OrderItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<OrderItemDto> getOrderItemsByOrderId(Integer orderId) {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        String sql = "SELECT * from food_orders WHERE order_id = " + orderId;
        try {
            List<Map<String, Object>> orderItemsMap = jdbcTemplate.queryForList(sql);
            for (Map<String, Object> map : orderItemsMap){
                OrderItemDto orderItem = new OrderItemDto();
                orderItem.setOrderId((Integer) map.get("order_id"));
                orderItem.setProductId((Integer) map.get("product_id"));
                orderItem.setQuantity((Integer) map.get("quantity"));
                orderItemDtoList.add(orderItem);
            }
            return orderItemDtoList;
        } catch (EmptyResultDataAccessException e) {
            return orderItemDtoList;
        }
    }
}
