package com.example.espappversion2;

public class Ingredient {
    private Food food;
    private double quantity;

    public Ingredient(){

    }

    public Ingredient(Food food, double quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Stock makeStock() {
        return new Stock(this.food, this.quantity, null, false, null);
    }

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

}
