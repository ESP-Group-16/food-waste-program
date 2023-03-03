package com.example.espappversion2;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Recipe {
    private int recipeId;
    private String name;
    private String imageURL;
    private int duration; // In minutes
    private ArrayList<String> dietaryInfo;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;
    private ArrayList<String> category;
    private User creator;

    public Recipe() {

    }

    public Recipe(int recipeId, String name, String imageURL, int duration, ArrayList<String> dietaryInfo, ArrayList<Ingredient> ingredients, ArrayList<String> steps, ArrayList<String> category, User creator) {
        this.recipeId = recipeId;
        this.name = name;
        this.imageURL = imageURL;
        this.duration = duration;
        this.dietaryInfo = dietaryInfo;
        this.ingredients = ingredients;
        this.steps = steps;
        this.category = category;
        this.creator = creator;
    }

    // Attribute Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getDietaryInfo() {
        return dietaryInfo;
    }

    public void setDietaryInfo(ArrayList<String> dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
