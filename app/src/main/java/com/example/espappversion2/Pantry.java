package com.example.espappversion2;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class Pantry {

    /**
     * Constant for pantry storage locations <p>
     *     0: fridge | 1: freezer | 2: cupboard
     * </p>
     */
    public static final String[] STORAGE_LOCATIONS = new String[] {"fridge", "freezer", "cupboard"};
    private int pantryId;
    private String pantryName;
    // New added attributes
    private ArrayList<User> users;
    private HashMap<String, ArrayList<Stock>> pantryItems;

    public Pantry() {
        pantryItems = new HashMap<>();
        pantryItems.put(STORAGE_LOCATIONS[0], new ArrayList<>());
        pantryItems.put(STORAGE_LOCATIONS[1], new ArrayList<>());
        pantryItems.put(STORAGE_LOCATIONS[2], new ArrayList<>());
    }

    public void addItemToPantry(int storageLocationIndex, Stock stock) {
        if(storageLocationIndex >=0 && storageLocationIndex <= 2 && stock != null) {
            this.pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).add(stock);
        }
    }

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

    public HashMap<String, ArrayList<Stock>> getPantryItems() {
        return pantryItems;
    }

    public void setPantryItems(HashMap<String, ArrayList<Stock>> pantryItems) {
        this.pantryItems = pantryItems;
    }
}
