package com.example.robottest.controller;

import com.example.robottest.exception.OrderException;
import com.example.robottest.exception.OrderProcessException;
import com.example.robottest.model.Components;
import com.example.robottest.model.Order;
import com.example.robottest.service.impl.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  testing the order status (response status) in case:
 *  - order valid
 *  - order invalid
 *  - order's configuration invalid
 */
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper ;

    @MockBean
    private OrderService orderService;

    private static final String URL = "/orders" ;


    @Test
    void create_valid_order() throws Exception {
        Order order = new Order();
        order.setOrderID("order_id");
        order.setTotal(160.11f);
        when(orderService.createOrder(new ArrayList<>(Arrays.asList("I","A","D","F"))))
                .thenReturn(order);
        Components components = new Components();
        components.setComponents(new String[]{"I","A","D","F"});
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString( components)))
                .andExpect(status().isOk());
    }

    @Test
    void create_invalid_order() throws Exception {
        when(orderService.createOrder(new ArrayList<>(Arrays.asList("I"))))
                .thenThrow( OrderException.class);
        Components components = new Components();
        components.setComponents(new String[]{"I"});
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( components)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_order_invalid_cofiguration() throws Exception {
        when(orderService.createOrder(new ArrayList<>(Arrays.asList("B","A","D","F"))))
                .thenThrow(OrderProcessException.class);
        Components components = new Components();
        components.setComponents(new String[]{"B","A","D","F"});
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString( components)))
                .andExpect(status().isUnprocessableEntity());
    }

}