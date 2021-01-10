package com.teo.restaurants.repository;

import com.teo.restaurants.mapper.RestaurantMapper;
import com.teo.restaurants.model.Restaurant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            Restaurant restaurant = jdbcTemplate.queryForObject(sql, new RestaurantMapper(), restaurantName);
            return Optional.of(restaurant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Restaurant> findRestaurantById(Integer restaurantId) {
        String sql = "SELECT * from restaurants WHERE id = ?";
        try {
            Restaurant restaurant = jdbcTemplate.queryForObject(sql, new RestaurantMapper(), restaurantId);
            return Optional.of(restaurant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Restaurant mapRestaurant(Map<String, Object> map) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId((Integer) map.get("id"));
        restaurant.setName((String) map.get("name"));
        restaurant.setDescription((String) map.get("description"));
        restaurant.setOpeningTime((String) map.get("opening_time"));
        restaurant.setClosingTime((String) map.get("closing_time"));
        restaurant.setPriceCategory((String) map.get("price_category"));
        restaurant.setType((String) map.get("type"));
        return restaurant;
    }

    private List<Restaurant> findRestaurantsByGivenSql(String sql) {
        List<Map<String, Object>> restaurants = jdbcTemplate.queryForList(sql);
        List<Restaurant> restaurantList = new ArrayList<>();
        for (Map<String, Object> map : restaurants) {
            Restaurant restaurant = mapRestaurant(map);
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }

    public List<Restaurant> findAll() {
        String sql = "SELECT * from restaurants order by name";
        try {
            return findRestaurantsByGivenSql(sql);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Restaurant> findAllByType(String type) {
        String sql = "SELECT * from restaurants where type = '" + type.toLowerCase() + "'";
        try {
            return findRestaurantsByGivenSql(sql);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Restaurant> findAllByPriceCategory(String priceCategory) {
        String sql = "SELECT * from restaurants where price_category = '" + priceCategory.toLowerCase() + "'";
        try {
            return findRestaurantsByGivenSql(sql);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
