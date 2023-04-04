package com.example.espappversion2;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Food {
    private int foodId;
    private String name;
    private ArrayList<String> dietaryInfo;
    private String unit;
    private HashMap<String, Integer> nutritional_info;
    private double carbonFootprint;

    // TODO: clarify the functionality of this
    enum StorageLocation {
        FRIDGE,
        FREEZER,
        CUPBOARD
    }

    public Food() {
    }


    public Food(int foodId, String name, ArrayList<String> dietaryInfo, String unit, HashMap<String, Integer> nutritional_info, double carbonFootprint) {
        this.foodId = foodId;
        this.name = name;
        this.dietaryInfo = dietaryInfo;
        this.unit = unit;
        this.nutritional_info = nutritional_info;
        this.carbonFootprint = carbonFootprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDietaryInfo() {
        return dietaryInfo;
    }

    public void setDietaryInfo(ArrayList<String> dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Dictionary<String, Integer> getNutritional_info() {
        return nutritional_info;
    }

    public void setNutritional_info(Dictionary<String, Integer> nutritional_info) {
        this.nutritional_info = nutritional_info;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    public void setCarbonFootprint(double carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
    }

//    public StorageLocation getStorageLocation() {
//        return storageLocation;
//    }

//    public void setStorageLocation(StorageLocation storageLocation) {
//        this.storageLocation = storageLocation;
//    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
