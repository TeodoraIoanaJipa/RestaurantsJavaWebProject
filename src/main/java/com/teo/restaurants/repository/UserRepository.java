package com.teo.restaurants.repository;

import com.teo.restaurants.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> getUserById(Integer id) {
        String sql = "SELECT * from users WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                User user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setFirstName(resultSet.getString("first_name"));
                user1.setLastName(resultSet.getString("last_name"));
                user1.setUsername(resultSet.getString("username"));
                user1.setEmail(resultSet.getString("email"));
                return user1;
            }, id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
