package com.example.orderpad;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;  // Default quantity is initialized here if needed

    // Existing constructor
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 0;  // Default to 0 if not specified
    }

    // Overloaded constructor for all properties including quantity
    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) quantity--;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
