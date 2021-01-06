package com.teo.restaurants.repository;

import com.teo.restaurants.model.FoodProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FoodProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(FoodProduct foodProduct) {
        String sql = "INSERT into food_product VALUES(NULL, ?, ?, ?)";
        jdbcTemplate.update(sql, foodProduct.getName(), foodProduct.getDescription(),
                foodProduct.getPrice());
    }

    public Optional<FoodProduct> getFoodProductByIdAndRestaurantId(Integer id, Integer restaurantId) {
        String sql = "SELECT * from food_product WHERE id = ? AND restaurant_id = ?";
        try {
            FoodProduct product = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                FoodProduct foodProduct = new FoodProduct();
                foodProduct.setId(resultSet.getInt("id"));
                foodProduct.setName(resultSet.getString("name"));
                foodProduct.setDescription(resultSet.getString("description"));
                foodProduct.setPrice(resultSet.getDouble("price"));
                return foodProduct;
            }, id, restaurantId);
            if(product != null){
                return Optional.of(product);
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
