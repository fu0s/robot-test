package com.example.robottest.controller;


import com.example.robottest.model.Components;
import com.example.robottest.model.Order;
import com.example.robottest.model.Part;
import com.example.robottest.model.Stock;
import com.example.robottest.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * rest controller for ordering robots
     * @param components codes  of the components to order
     * @return order
     */
    @PostMapping
    public Order createApplication(@RequestBody Components components){
        return orderService.createOrder(new ArrayList<>(Arrays.asList(components.getComponents())) );
    }

    /**
     * list all the parts in stock
     * @return list of parts in stock
     */
    @GetMapping(value = "all")
    public ArrayList<Part>  getStock(){
        return Stock.getParts();
    }
}
