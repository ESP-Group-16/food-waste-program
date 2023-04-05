package com.example.espappversion2;

import org.json.JSONArray;
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
        
        if (recipe.has("meals")) {
            if (recipe.isNull("meals")) return;
            recipe = recipe.getJSONArray("meals").getJSONObject(0); // this line is necessary only if you pass the full json object
        }
        this.recipeId = Integer.parseInt(recipe.getString("idMeal"));
        this.name = recipe.getString("strMeal");
        this.imageURL = recipe.getString("strMealThumb");
        this.duration = 10;  // TODO: we don't have a duration right now
        this.dietaryInfo = dietaryInfo; // TODO: get this from some API
        Pattern patternUnit = Pattern.compile("\\b(?![a-zA-Z]*\\d)\\w+\\b"); // gets any word that doesn't have a number in it i.e. 1/2 used for unit
        Pattern patternQuantity = Pattern.compile("\\d+"); // gets any word that doesn't have a number in it i.e. 1/2 used for unit

        this.category = new ArrayList<String>();
        this.ingredients = new ArrayList<Ingredient>();
        if (recipe.has("strInstructions")) { // if we pass the detailed version of the recipe
            // iterating through the 20 ingredients and measures. Not 0-indexed so that's why we start at 1
            for (int i = 1; i <= 20; i++) {
                if (recipe.getString("strIngredient" + i) == "") break;
                Matcher m_unit = patternUnit.matcher(recipe.getString("strMeasure" + i));
                String unit = m_unit.find() ? m_unit.group() : "g";

                Matcher m_quantity = patternQuantity.matcher(recipe.getString("strMeasure" + i));
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

            category.add(recipe.getString("strCategory"));
            category.add(recipe.getString("strArea"));
        }
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

    public static ArrayList<Recipe> generateRecipesGivenJSON(JSONObject json) throws JSONException {
        ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
        if (!json.isNull("meals")) {
            JSONArray jsonArr = json.getJSONArray("meals");
            for (int i = 0; i < jsonArr.length(); i++) {
                recipeArrayList.add(new Recipe(jsonArr.getJSONObject(i)));
            }
        }
        return recipeArrayList;
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
