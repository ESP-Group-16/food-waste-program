package com.example.espappversion2;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;


// singleton class to handle all SharedPreferences (local storage) methods
public class Utils {
    public static final String CURRENT_USER_KEY = "current_user";
    public static final String USERS_KEY = "users";
    private Context context;
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private static Utils instance;
    private User currentUser;


    // private constructor
    private Utils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("cardsDB", Context.MODE_PRIVATE);
        gson = new Gson();
        // initialize users list is null
       //clearUsers();
        if (getUsers() == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERS_KEY, gson.toJson(new Hashtable<String, User>()));
            editor.commit();
        }
    }

    // getter for the single instance of the class
    public static Utils getInstance(Context context) {
        if(instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        HashMap<String, User> users = getUsers();
        //System.out.println("Users in saved state: " + gson.toJson(users));
        editor.remove(USERS_KEY);
        editor.putString(USERS_KEY, gson.toJson(users));
        editor.commit();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // User Methods

    public User getCurrentUser() {
        return this.currentUser;
    }

    public User getCurrentUserFromMemory() {
        Type type = new TypeToken<String>(){}.getType();
        String userName = gson.fromJson(sharedPreferences.getString(CURRENT_USER_KEY, null), type);
        if (getUser(userName) != null) {
            this.currentUser = getUser(userName);
            return getUser(userName);
        }
        return null;
    }

    public void setCurrentUser(User user, boolean stayLoggedIn) {
        this.currentUser = user;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CURRENT_USER_KEY);
        if(stayLoggedIn) {
            editor.putString(CURRENT_USER_KEY, gson.toJson(currentUser.getUserName()));
            //Toast.makeText(context, user.getUserName() + " set as current user in memory", Toast.LENGTH_SHORT).show();
        }
        editor.commit();
    }

    public HashMap<String, User> getUsers() {
        System.out.println("getUsers: " + sharedPreferences.getString(USERS_KEY, null));
        Type type = new TypeToken<HashMap<String, User>>(){}.getType();
        try {
            return gson.fromJson(sharedPreferences.getString(USERS_KEY, null), type);
        } catch(IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userExists(String username) {
        if(username != null) {
            return getUsers().containsKey(username);
        }
        return false;
    }

    public boolean userExists(User user) {
        if(user != null) {
            return userExists(user.getUserName());
        }
        return false;
    }

    public User getUser(String username) {
        if(getUsers().get(username) != null) {
            return getUsers().get(username);
        }
        return null;
    }

    public void registerUser(User user) {
        HashMap<String, User> users = getUsers();
        users.put(user.getUserName(), user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERS_KEY);
        editor.putString(USERS_KEY, gson.toJson(users));
        editor.commit();
        System.out.println("registerUser: " + users);
        System.out.println("registerUser: " + getUsers());
    }

    public void updateUser(User user) {
        HashMap<String, User> users = getUsers();
        users.remove(user.getUserName());
        users.put(user.getUserName(), user);
        //System.out.println("Users in updateUser: " + gson.toJson(users));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERS_KEY);
        editor.putString(USERS_KEY, gson.toJson(users));
        editor.commit();

        //System.out.println("Users after saving: " + gson.toJson(users));
    }

    public void clearCurrentUser() {
        this.currentUser = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CURRENT_USER_KEY);
        editor.commit();
        //Toast.makeText(context, "Current user removed from memory", Toast.LENGTH_SHORT).show();
    }

    public void clearUsers() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERS_KEY);
        editor.commit();
    }


    // End of User Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pantry Methods

    public Pantry getPantryByUser(User user) {
        if(user != null) {
            return getUser(user.getUserName()).getPantry();
        }
        return null;
    }

    public void addPantryItem(String storageLocation, Stock stock) {
        User currentUser = getCurrentUser();
        currentUser.addItemToPantry(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), stock);
        updateUser(currentUser);
        saveState();
    }

    public void removePantryItem(String storageLocation, int index) {
        User currentUser = getCurrentUser();
        currentUser.removeItemFromPantry(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), index);
        updateUser(currentUser);
        saveState();
    }

    public void editPantryItemQuantity(String storageLocation, int stockIndex, double quantityToRemove) {
        User currentUser = getCurrentUser();
        currentUser.editPantryItemQuantity(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), stockIndex, quantityToRemove);
        updateUser(currentUser);
        saveState();
    }


    // End of Pantry Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Shopping List Methods

    public HashMap<String, ArrayList<Stock>> getShoppingListByUser(User user) {
        if(user != null) {
            return getUser(user.getUserName()).getShoppingList();
        }
        return null;
    }

    public void addShoppingListItem(String storageLocation, Stock stock) {
        User currentUser = getCurrentUser();
        currentUser.addItemToShoppingList(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), stock);
        updateUser(currentUser);
        saveState();
    }

    public void removeShoppingListItem(String storageLocation, int index) {
        User currentUser = getCurrentUser();
        currentUser.removeItemFromShoppingList(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), index);
        updateUser(currentUser);
        saveState();
    }

    public boolean isShoppingListEmpty(User user) {
        return getShoppingListByUser(user).isEmpty();
    }

    // add selected items to pantry and remove them from shopping list
    public void addShoppingListItemsToPantry(String storageLocation, ArrayList<Stock> items) {
        for(Stock s : items) {
            addPantryItem(storageLocation, s);
            removeShoppingListItem(storageLocation, items.indexOf(s));
        }
    }


    // End of Shopping List Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Recipe Methods

    public ArrayList<String> getFavouriteRecipes() {
        ArrayList<String> favourites = new ArrayList<>();
        if(currentUser != null) {
            return currentUser.getFavouriteRecipes();
        }
        return favourites;
    }

    public void addRecipeToFavourites(String recipeName) {
        User currentUser = getCurrentUser();
        currentUser.addRecipeToFavourites(recipeName);
        updateUser(currentUser);
        saveState();
        //System.out.println("Users after adding recipe to favourites: " + gson.toJson(getUsers()));
        //Toast.makeText(context, recipeName + " added to favourite recipes", Toast.LENGTH_SHORT).show();
    }

    public void removeRecipeFromFavourites(String recipeName) {
        User currentUser = getCurrentUser();
        currentUser.removeRecipeFromFavourites(recipeName);
        updateUser(currentUser);
        saveState();
        //System.out.println("Users after removing recipe from favourites: " + gson.toJson(getUsers()));
        //Toast.makeText(context, recipeName + " removed from favourite recipes", Toast.LENGTH_SHORT).show();
    }

    public boolean isRecipeInFavourites(String recipeName) {
        return getFavouriteRecipes().contains(recipeName);
    }


    // End of Recipe Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
