package com.example.espappversion2;

import android.os.Build;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Stock {
    private Food food;
    private double quantity;
    private String expiresAt;
    private boolean isShared;
    private User owner;

    public Stock() {

    }

    public Stock(Food food, double quantity, String expiresAt, boolean isShared, User owner) {
        this.food = food;
        this.quantity = quantity;
        this.expiresAt = expiresAt;
        this.isShared = isShared;
        this.owner = owner;
    }

    // Methods
    public boolean checkExpired() {
        // TODO: make functionality for checkExpired.
        String dateString = expiresAt;

        // Parse the stored date-time string to LocalDateTime
        // Parse the stored date string to LocalDate
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
        }
        LocalDate storedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            storedDate = LocalDate.parse(dateString, formatter);
        }

        // Get the current date
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        // Compare the stored date with the current date
        boolean isPast = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            isPast = storedDate.isBefore(currentDate);
        }

        return isPast;
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
