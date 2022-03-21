package com.example.robottest.model;

import java.io.Serializable;

/**
 * Order object as Request's response
 */
public class Order implements Serializable {

    private String orderID;

    private Float  total;

    /**
     * empty constructor
     */
    public Order(){
        // empty constructor
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
