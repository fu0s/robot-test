package com.example.robottest.service.impl;

import com.example.robottest.exception.OrderException;
import com.example.robottest.exception.OrderProcessException;
import com.example.robottest.model.Order;
import com.example.robottest.model.Part;
import com.example.robottest.model.Stock;
import com.example.robottest.service.OrderServiceInt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OrderService  implements OrderServiceInt {

    private static final String INVALID_ORDER = "Invalid Order.";

    /**
     * function to purchase parts for robot
     * @param codes parts of robot to purchase
     * @return order or throw exception in case of invalid order
     */
    public Order createOrder(ArrayList<String> codes) {
        Order order = new Order();

        if (codes.size() != 4){
            throw new OrderException(INVALID_ORDER);
        }
        ArrayList<Part> orderParts = getPartsFromStock(codes);

        if (!checkValidRobotOrder(orderParts)){
            throw new OrderProcessException(INVALID_ORDER);
        }

        if (!checkPartsAvailibility(orderParts)){
            throw new OrderProcessException(INVALID_ORDER);
        }

        order.setOrderID(UUID.randomUUID().toString());
        order.setTotal(orderParts.stream().map(Part::getPrice).reduce(0f,Float::sum));

        updateStock(codes);
        return order;
    }

    /**
     * preparing the order by getting the list of parts from the stock
     * @param codes codes of the parts in order
     * @return Parts of stock
     */
    public ArrayList<Part> getPartsFromStock(ArrayList<String> codes){
        ArrayList<Part> orderParts;

        orderParts = Stock.getParts().stream().filter(x -> codes.contains(x.getCode())).collect(Collectors.toCollection(ArrayList::new));

        return orderParts;
    }

    /**
     *  checking if all parts are from different categories
     * @param orderParts order parts
     * @return true if all parts are from different cateories false if not
     */
    public boolean checkValidRobotOrder(ArrayList<Part> orderParts){
        return orderParts.stream().map(Part::getCategory).distinct().count() == 4 ;
    }

    /**
     *  checking if order parts are available in stock
     * @param orderParts order parts
     * @return true if all parts are available in stock false if not
     */
    public boolean checkPartsAvailibility(ArrayList<Part> orderParts){
        return orderParts.stream().map(Part::getAvailable).filter(x -> x > 0).count() == 4 ;
    }

    /**
     * updating the stock after the purchase
     * @param codes parts purchased
     */
    public void updateStock(ArrayList<String> codes){
        Stock.setParts(Stock.getParts().stream().map( x->{
            if (codes.contains(x.getCode())){
                x.setAvailable(x.getAvailable() - 1);
            }
            return x;
        }).collect(Collectors.toCollection(ArrayList::new)));
    }
}
