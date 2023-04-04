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

    public void removeItemFromPantry(int storageLocationIndex, int index) {
        if(storageLocationIndex >=0 && storageLocationIndex <= 2) {
            if(index >= 0 && index <= pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).size()) {
                System.out.println("Pantry.remove");
                pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).remove(index);
                System.out.println(this.pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).size());
            }
        }
    }

    public void editPantryItemQuantity(int storageLocationIndex, int index, double quantityToRemove) {
        if(storageLocationIndex >=0 && storageLocationIndex <= 2){
            if(index >= 0 && index <= pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).size()) {
                double originalQuantity = pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).get(index).getQuantity();
                if(originalQuantity > quantityToRemove) {
                    // (double) Math.round((stockitem.getQuantity() - quantityToRemove) * 100) / 100
                    pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).get(index).setQuantity((double) Math.round((originalQuantity - quantityToRemove) * 100) / 100);
                    if(pantryItems.get(STORAGE_LOCATIONS[storageLocationIndex]).get(index).getQuantity() == 0.0) {
                        removeItemFromPantry(storageLocationIndex, index);
                    }
                } else {
                    removeItemFromPantry(storageLocationIndex, index);
                }
            }
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
