package com.example.madcourse2024;

public class Product {
    private int id;
    private String title;
    private float price;

    public Product(int i, String t, float p) {
        id = i;
        title = t;
        price = p;
    }

    public Product() {
        id = 0;
        title = "";
        price = 0.0f;
    }

    public String stringify() {
        return "ID: " + id + " - Name: " + title + " - price: " + price;
    }

    public void setId(int v) { id = v; }
    public void setTitle(String v) { title = v; }
    public void setPrice(float v) { price = v; }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public float getPrice() { return price; }
}
