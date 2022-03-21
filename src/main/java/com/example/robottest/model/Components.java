package com.example.robottest.model;

/**
 * the class of the order (request)
 */
public class Components  {


    private String[] components;

    public Components(){}

    public Components(String[] components) {
        this();
        this.components = components;
    }

    public String[] getComponents() {
        return components;
    }

    public void setComponents(String[] components) {
        this.components = components;
    }
}
