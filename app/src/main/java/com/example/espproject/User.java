package com.example.espproject;

import java.util.ArrayList;

public class User {
    private int userId;
    private String userName;
    private String password;
    private ArrayList<Food> dislikes;
    private boolean hasDoneTutorial;
    private ArrayList<Food> allergies;
    private ArrayList<Recipe> favourites; // in UML diagram is ArrayListed as ArrayList<Recipes>; changed this to <Recipe>.
    private int socialCreditScore;
    private ArrayList<Ingredient> shoppingArrayList;

    public static void signUp() { // TODO: Is this the same as login() - seen below?

    }

    public User(int userId, String username) {
        this.userId = userId;
        this.userName = username;
    }

    public User() {

    }

    // Requested Methods (from UML)

    public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> foo = null; // TODO: exchange placeholder.
        return foo;
    }

    // getFavouriteRecipes() same functionality as 'getter' getfavourites()
    public ArrayList<Recipe> getFavouriteRecipes() {
        return favourites;
    }

    // getAllergies in New Getters and Setters

    // TODO: !! I kinda had to guess what we're returning here
    public ArrayList<String> getPreferences(){
        ArrayList<String> foo = null; // TODO: exchange placeholder.
        return foo;
    }

    public void login(){

    }

    public void logout(){

    }


    // Old Getters and Setters (before template created)

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    // New Getters and Setters

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

    // getFavourites would go here.

    public void setFavourites(ArrayList<Recipe> favourites) {
        this.favourites = favourites;
    }

    public int getSocialCreditScore() {
        return socialCreditScore;
    }

    public void setSocialCreditScore(int socialCreditScore) {
        this.socialCreditScore = socialCreditScore;
    }

    public ArrayList<Ingredient> getShoppingArrayList() {
        return shoppingArrayList;
    }

    public void setShoppingArrayList(ArrayList<Ingredient> shoppingArrayList) {
        this.shoppingArrayList = shoppingArrayList;
    }
}
