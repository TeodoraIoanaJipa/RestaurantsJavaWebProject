package com.teo.restaurants.repository;

import com.teo.restaurants.model.Restaurant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RestaurantsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RestaurantsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Restaurant restaurant) {
        String sql = "INSERT into restaurants VALUES(NULL, ?, ?, ?, ?, ?, ? )";
        jdbcTemplate.update(sql, restaurant.getName(), restaurant.getDescription(),
                restaurant.getOpeningTime(), restaurant.getClosingTime(),
                restaurant.getPriceCategory(), restaurant.getType());
    }

    public Optional<Restaurant> getRestaurantByName(String restaurantName) {
        String sql = "SELECT * from restaurants WHERE name = ?";
        try {
            Restaurant restaurant = jdbcTemplate.queryForObject(sql, new RowMapper<Restaurant>() {
                @Override
                public Restaurant mapRow(ResultSet resultSet, int i) throws SQLException {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setId(resultSet.getInt("id"));
                    restaurant.setName(resultSet.getString("name"));
                    restaurant.setDescription(resultSet.getString("description"));
                    restaurant.setOpeningTime(resultSet.getString("opening_time"));
                    restaurant.setClosingTime(resultSet.getString("closing_time"));
                    restaurant.setPriceCategory(resultSet.getString("price_category"));
                    return restaurant;
                }
            }, restaurantName);
            return Optional.of(restaurant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Restaurant> getRestaurantById(Integer restaurantId) {
        String sql = "SELECT * from restaurants WHERE id = ?";
        try {
            Restaurant restaurant = jdbcTemplate.queryForObject(sql, new RowMapper<Restaurant>() {
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
            }, restaurantId);
            return Optional.of(restaurant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Restaurant> findAll() {
        String sql = "SELECT * from restaurants ";
        try {
            List<Map<String, Object>> restaurants = jdbcTemplate.queryForList(sql);
            List<Restaurant> restaurantList = new ArrayList<>();
            for (Map<String, Object> map : restaurants) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId((Integer) map.get("id"));
                restaurant.setName((String) map.get("name"));
                restaurant.setDescription((String) map.get("description"));
                restaurant.setOpeningTime((String) map.get("opening_time"));
                restaurant.setClosingTime((String) map.get("closing_time"));
                restaurant.setPriceCategory((String) map.get("price_category"));
                restaurant.setType((String) map.get("type"));
                restaurantList.add(restaurant);
            }
            return restaurantList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
