package com.example.robottest.service.impl;

import com.example.robottest.exception.OrderException;
import com.example.robottest.exception.OrderProcessException;
import com.example.robottest.model.Category;
import com.example.robottest.model.Order;
import com.example.robottest.model.Part;
import com.example.robottest.model.Stock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * testing order's service in case :
 * - order valid
 * - order invalid
 * - order's configuration invalid
 * - no available parts in stock
 */
@SpringBootTest
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService = new OrderService();


    @Test
    void create_order_should_order_a_robot() {
        Stock.setParts(initStock());
        ArrayList<String> codes  = new ArrayList<>(Arrays.asList("I","A","D","F"));
        Order order = orderService.createOrder(codes);
        assertNotNull(order.getOrderID());
        assertEquals(160.11f,order.getTotal(),0.01);
    }

    @Test
    void create_order_should_not_allow_invalid_body() {
        Stock.setParts(initStock());
        ArrayList<String> codes  = new ArrayList<>(Arrays.asList("I"));
        assertThrows(OrderException.class, () -> {
            Order order = orderService.createOrder(codes);
        });

    }

    @Test
    void create_order_should_not_allow_invalid_robot_configuration() {
        Stock.setParts(initStock());
        ArrayList<String> codes  = new ArrayList<>(Arrays.asList("B","A","D","F"));
        assertThrows(OrderProcessException.class, () -> {
            Order order = orderService.createOrder(codes);
        });
    }

    @Test
    void create_order_should_not_allow_no_available_parts_in_stock() {
        Stock.setParts(initStock());
        ArrayList<String> codes  = new ArrayList<>(Arrays.asList("I","C","D","F"));
        assertThrows(OrderProcessException.class, () -> {
            Order order = orderService.createOrder(codes);
        });
    }



    public ArrayList<Part> initStock(){
        ArrayList<Part> stock = new ArrayList<>();
        stock.add(new Part("A" , 10.28f, 9 , "Humanoid Face"         , Category.FACE));
        stock.add(new Part("B" , 24.07f, 7 , "LCD Face"              , Category.FACE));
        stock.add(new Part("C" , 13.30f, 0 , "Steampunk Face"        , Category.FACE));
        stock.add(new Part("D" , 28.94f, 1 , "Arms with Hands"       , Category.ARMS));
        stock.add(new Part("E" , 12.39f, 3 , "Arms with Grippers"    , Category.ARMS));
        stock.add(new Part("F" , 30.77f, 2 , "Mobility with Wheels"  , Category.MOBILITY));
        stock.add(new Part("G" , 55.13f, 15, " Mobility with Legs"  , Category.MOBILITY));
        stock.add(new Part("H" , 50.00f, 7 , "Mobility with Tracks"  , Category.MOBILITY));
        stock.add(new Part("I" , 90.12f, 92, " Material Bioplastic" , Category.MATERIAL));
        stock.add(new Part("J" , 82.31f, 15, "Material Metallic"    , Category.MATERIAL));
        return stock;
    }
}