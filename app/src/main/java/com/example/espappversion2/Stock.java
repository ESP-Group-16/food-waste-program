package com.example.espappversion2;

public class Stock {
    private Food food;
    private double quantity;
    private String expiresAt;
    private boolean isShared;
    private User owner;

    public Stock(){

    }

    public Stock(Food food, double quantity, String expiresAt, boolean isShared, User owner) {
        this.food = food;
        this.quantity = quantity;
        this.expiresAt = expiresAt;
        this.isShared = isShared;
        this.owner = owner;
    }

    // Methods
    public boolean checkExpired(){
        // TODO: make functionality for checkExpired.
        return true;
    }

    // Getters and Setters

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
