package com.example.robottest.model;

/**
 * parts of robot
 */
public class Part {

    private String code;

    private float price;

    private int available;

    private String name;

    private Category category;

    public Part(String code, float price, int available, String name, Category category) {
        this.code = code;
        this.price = price;
        this.available = available;
        this.name = name;
        this.category = category;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
