package com.example.espappversion2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recipe {
    private int recipeId;
    private String name;
    private String imageURL;
    private int duration; // In minutes
    private ArrayList<String> dietaryInfo;
    private ArrayList<Ingredient> ingredients;
    private String steps;
    private ArrayList<String> category;
    private User creator;

    public Recipe() {

    }

    public Recipe(JSONObject recipe) throws JSONException {
        recipe = recipe.getJSONArray("meals").getJSONObject(0);
        this.recipeId = Integer.parseInt(recipe.getString("idMeal"));
        this.name = recipe.getString("strMeal");
        this.imageURL = recipe.getString("strMealThumb");
        this.duration = 10;  // TODO: we don't have a duration right now
        this.dietaryInfo = dietaryInfo; // TODO: get this from some API
        Pattern patternUnit = Pattern.compile("\\b(?![a-zA-Z]*\\d)\\w+\\b"); // gets any word that doesnt have a number in it i.e. 1/2 used for unit
        Pattern patternQuantity = Pattern.compile("\\b\\w*\\d\\w*\\b"); // gets any word that doesnt have a number in it i.e. 1/2 used for unit

        this.ingredients = new ArrayList<Ingredient>();
        // iterating through the 20 ingredients and measures. Not 0-indexed so that's why we start at 1
        for (int i = 1; i <= 20; i++) {
            if (recipe.getString("strIngredient" + i) == "") break;
            Matcher m_unit = patternUnit.matcher(recipe.getString("strMeasure"+i));
            String unit = m_unit.find() ? m_unit.group() : "g";

            Matcher m_quantity = patternQuantity.matcher(recipe.getString("strMeasure"+i));
            double quantity = Double.parseDouble(m_quantity.find() ? m_quantity.group() : "1");
            Food food = new Food(
                    1,
                    recipe.getString("strIngredient" + i),
                    new ArrayList<String>(),    // TODO: this is dietaryInfo
                    unit,
                    new HashMap<String, Integer>(), // TODO: this is nutritionalInfo
                    0.0 // TODO: get carbon using API
            );


            Ingredient ingredient = new Ingredient(
                    food,
                    quantity
            );


            this.ingredients.add(ingredient);
        }
        this.steps = recipe.getString("strInstructions");

        ArrayList<String> categories = new ArrayList<String>();
        categories.add(recipe.getString("strCategory"));
        categories.add(recipe.getString("strArea"));

        this.category = categories;
    }

    public Recipe(int recipeId, String name, String imageURL, int duration, ArrayList<String> dietaryInfo, ArrayList<Ingredient> ingredients, String steps, ArrayList<String> category, User creator) {
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

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
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
