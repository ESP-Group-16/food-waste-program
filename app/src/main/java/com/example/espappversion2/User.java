package com.example.espappversion2;

import static com.example.espappversion2.Pantry.STORAGE_LOCATIONS;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int userId;
    private String userName;
    private String password;
    private ArrayList<Food> dislikes;
    private boolean hasDoneTutorial;
    private ArrayList<String> allergies;
    private ArrayList<String> favouriteRecipes;
    private int socialCreditScore;
    private HashMap<String, ArrayList<Stock>> shoppingList;
    private Pantry pantry;

    public User(String userName, String password) {
        this.userName=userName;
        this.password=password;
        this.pantry = new Pantry();
        this.allergies = new ArrayList<>();
        this.favouriteRecipes = new ArrayList<>();
        shoppingList = new HashMap<>();
        shoppingList.put(STORAGE_LOCATIONS[0], new ArrayList<>());
        shoppingList.put(STORAGE_LOCATIONS[1], new ArrayList<>());
        shoppingList.put(STORAGE_LOCATIONS[2], new ArrayList<>());
    }

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

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

    public void removeAllergy(int i) {
        this.allergies.remove(i);
    }

    public void addRecipeToFavourites(String recipeName) {
        this.favouriteRecipes.add(recipeName);
    }

    public void removeRecipeFromFavourites(String recipeName) {
        this.favouriteRecipes.remove(recipeName);
    }

    public ArrayList<String> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(ArrayList<String> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public int getSocialCreditScore() {
        return socialCreditScore;
    }

    public void setSocialCreditScore(int socialCreditScore) {
        this.socialCreditScore = socialCreditScore;
    }

    public HashMap<String, ArrayList<Stock>> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(HashMap<String, ArrayList<Stock>> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void addItemToShoppingList(int storageLocationIndex, Stock item) {
        String key = STORAGE_LOCATIONS[storageLocationIndex];
        if(this.shoppingList.containsKey(key) && this.shoppingList.get(key) != null) {
            this.shoppingList.get(STORAGE_LOCATIONS[storageLocationIndex]).add(item);
        }
    }

    public void removeItemFromShoppingList(int storageLocationIndex, int index) {
        String key = STORAGE_LOCATIONS[storageLocationIndex];
        System.out.println("1");
        if(storageLocationIndex >=0 && storageLocationIndex <= 2) {
            System.out.println("2");
            if(index >= 0 && index < this.shoppingList.get(key).size()) {
                System.out.println("3");
                this.shoppingList.get(key).remove(index);
            }
        }
    }

    public void addItemToPantry(int storageLocation, Stock item) {
        pantry.addItemToPantry(storageLocation, item);
    }

    public void removeItemFromPantry(int storageLocation, int index) {
        pantry.removeItemFromPantry(storageLocation, index);
    }

    public void editPantryItemQuantity(int storageLocation, int index, double quantityToRemove) {
        pantry.editPantryItemQuantity(storageLocation, index, quantityToRemove);
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }
}
