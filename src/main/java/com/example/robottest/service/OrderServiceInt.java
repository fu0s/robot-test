package com.example.robottest.service;

import com.example.robottest.model.Order;
import com.example.robottest.model.Part;

import java.util.ArrayList;

public interface OrderServiceInt {

    public Order createOrder(ArrayList<String> codes);

    public ArrayList<Part> getPartsFromStock(ArrayList<String> codes);

    public boolean checkValidRobotOrder(ArrayList<Part> orderParts);

    public boolean checkPartsAvailibility(ArrayList<Part> orderParts);

    public void updateStock(ArrayList<String> codes);

}
