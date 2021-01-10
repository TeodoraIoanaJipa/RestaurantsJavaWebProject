package com.teo.restaurants.controller;

import com.teo.restaurants.model.Order;
import com.teo.restaurants.model.Restaurant;
import com.teo.restaurants.model.User;
import com.teo.restaurants.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    private Order order;

    @BeforeEach
    private void setupOrder() {
        order = new Order();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        order.setRestaurant(restaurant);

        User user = new User();
        user.setId(1);
        order.setUser(user);

        order.setOrderId(1);
    }

    @Test
    void getOrdersByUserId() throws Exception {
        Integer userId = 1;
        Mockito.when(orderService.findAllOrdersByUserId(userId)).thenReturn(List.of(order));
        String url = "/order/get-by-user?userId=" + userId.toString();

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}