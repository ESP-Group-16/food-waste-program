package com.example.espproject;

import java.util.Date; // Imported for expiresAt

public class Stock { // TODO: check access modifiers.
    private Food food;
    private double quantity;
    private Date expiresAt;
    private boolean isShared;
    private User owner;

    public Stock(){
        // TODO: (verify the need for)/ edit constructor
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

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
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
