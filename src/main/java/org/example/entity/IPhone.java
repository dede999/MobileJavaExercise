package org.example.entity;

public class IPhone {
    private String model;
    private String color;
    private int price;

    public IPhone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }
}
