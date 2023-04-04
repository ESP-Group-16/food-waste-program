package com.example.espappversion2;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;


// singleton class to handle all SharedPreferences methods
public class Utils {
    public static final String CURRENT_USER_KEY = "current_user";
    public static final String USERS_KEY = "users";

    private Context context;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    private static Utils instance;

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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // User Methods

    public User getCurrentUser() {
        Type type = new TypeToken<String>(){}.getType();
        //System.out.println(sharedPreferences.getString(CURRENT_USER_KEY, null));
        String userName = gson.fromJson(sharedPreferences.getString(CURRENT_USER_KEY, null), type);
        if (getUser(userName) != null) {
            return getUser(userName);
        }
        return null;
    }

    public void setCurrentUser(User user, boolean stayLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CURRENT_USER_KEY);
        if(stayLoggedIn) {
            editor.putString(CURRENT_USER_KEY, gson.toJson(user.getUserName()));
        }
        editor.commit();
    }

    // get all existing users
    public HashMap<String, User> getUsers() {
        //System.out.println("getUsers: " + sharedPreferences.getString(USERS_KEY, null));
        Type type = new TypeToken<HashMap<String, User>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(USERS_KEY, null), type);
    }

    public boolean userExists(String username) {
        return getUsers().containsKey(username);
    }

    public boolean userExists(User user) {
        return getUsers().containsValue(user);
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

    // will implement later - low priority
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

    // might not need this
    public void removeUser(User user) {

    }

    public void clearCurrentUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CURRENT_USER_KEY);
        editor.commit();
    }

    // clears all users - for debugging purposes
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
        //System.out.println("All users before adding: " + gson.toJson(getUsers()));
        //System.out.println("Items in pantry before adding: " + gson.toJson(currentUser.getPantry()));
        currentUser.addItemToPantry(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), stock);
        //System.out.println("Items in pantry after adding: " + gson.toJson(currentUser.getPantry()));
        updateUser(currentUser);
        saveState();
    }

    public void removePantryItem(String storageLocation, int index) {
        User currentUser = getCurrentUser();
        //System.out.println("All users before removing: " + gson.toJson(getUsers()));
        //System.out.println("Items in pantry before removing: " + gson.toJson(currentUser.getPantry()));
        currentUser.removeItemFromPantry(Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation), index);
        //System.out.println("Items in pantry after removing: " + gson.toJson(currentUser.getPantry()));
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

    public void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        HashMap<String, User> users = getUsers();
        //System.out.println("Users in saved state: " + gson.toJson(users));
        editor.remove(USERS_KEY);
        editor.putString(USERS_KEY, gson.toJson(users));
        editor.commit();
    }
}
