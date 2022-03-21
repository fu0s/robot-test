package com.example.robottest.model;

import java.util.ArrayList;

/**
 * the stock of robot's parts
 */
public class Stock {

    static ArrayList<Part> parts;

    private Stock() {
    }

    public static ArrayList<Part> getParts() {
        return parts;
    }

    public static void setParts(ArrayList<Part> parts) {
        Stock.parts = parts;
    }
}
