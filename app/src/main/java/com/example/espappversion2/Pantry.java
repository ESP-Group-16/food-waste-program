package com.example.espappversion2;

import java.util.ArrayList;

public class Pantry {

    private int pantryId;
    private String pantryName;
    // New added attributes
    private ArrayList<User> users;
    private ArrayList<Stock> stock;

    public Pantry(int pantryId, String pantryName, ArrayList<User> users, ArrayList<Stock> stock) {
        this.pantryId = pantryId;
        this.pantryName = pantryName;
        this.users = users;
        this.stock = stock;
    }

    public Pantry() {

    }

//    public Pantry retrievePantry(FirebaseFirestore db) {
//        //TODO: move this to the interface method for future use
//
//        // pass the pantry back to pantry activity
//        db.collection(fireStoreCollectionName).document("PsFFlfX7gicMjIyBay5H").get();
//
//        return null;
//    }


    public int getPantryId() {
        return pantryId;
    }

    public void setPantryId(int pantryId) {
        this.pantryId = pantryId;
    }

    public String getPantryName() {
        return pantryName;
    }

    public void setPantryName(String pantryName) {
        this.pantryName = pantryName;
    }

    // New Attribute Getters and Setters

    public ArrayList<User> getUsers() { // Name altered from getUserArrayList() --> getUsers() (per UML diagram)
        return users;
    }

    public void setUsers(ArrayList<User> userArrayList) { // setUserArrayList() --> setUsers()
        this.users = userArrayList;
    }

    public ArrayList<Stock> getStock() { // Name altered from getStocks() --> getStock() (per UML diagram)
        return stock;
    }

    public void setStock(ArrayList<Stock> stocks) { // setStocks() --> setStock()
        this.stock = stocks;
    }
}
