package com.example.espappversion2;

import java.util.ArrayList;

public class User {
    // TODO: verify the need for this class - if Firebase takes care of it
    private int userId;
    private String userName;
    private String password;
    private ArrayList<Food> dislikes;
    private boolean hasDoneTutorial;
    private ArrayList<Food> allergies;
    private ArrayList<Recipe> favouriteRecipes;
    private int socialCreditScore;
    private ArrayList<Ingredient> shoppingList;


    public User() {

    }

    // TODO: finish getPreferences()
    public ArrayList<String> getPreferences(){
        ArrayList<String> foo = null;
        return foo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Food> getDislikes() {
        return dislikes;
    }

    public void setDislikes(ArrayList<Food> dislikes) {
        this.dislikes = dislikes;
    }

    public boolean isHasDoneTutorial() {
        return hasDoneTutorial;
    }

    public void setHasDoneTutorial(boolean hasDoneTutorial) {
        this.hasDoneTutorial = hasDoneTutorial;
    }

    public ArrayList<Food> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<Food> allergies) {
        this.allergies = allergies;
    }

    public ArrayList<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(ArrayList<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public int getSocialCreditScore() {
        return socialCreditScore;
    }

    public void setSocialCreditScore(int socialCreditScore) {
        this.socialCreditScore = socialCreditScore;
    }

    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
