package com.example.espappversion2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


// singleton class to handle all SharedPreferences methods
public class Utils {
    public static final String CURRENT_USER_KEY = "current_user";
    public static final String USERS_KEY = "users";

    private Context context;
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private User currentUser;

    private static Utils instance;

    // private constructor
    private Utils(Context context) {
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

    public User getCurrentUser() {
        Type type = new TypeToken<User>(){}.getType();
        System.out.println(sharedPreferences.getString(CURRENT_USER_KEY, null));
        return gson.fromJson(sharedPreferences.getString(CURRENT_USER_KEY, null), type);
    }

    public void setCurrentUser(User user, boolean stayLoggedIn) {
        this.currentUser = user;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CURRENT_USER_KEY);
        if(stayLoggedIn) {
            editor.putString(CURRENT_USER_KEY, gson.toJson(user));
        }
        editor.commit();
    }

    // get all existing users
    public HashMap<String, User> getUsers() {
        System.out.println("getUsers: " + sharedPreferences.getString(USERS_KEY, null));
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
        System.out.println(getUsers().getClass().getName());
        //System.out.println(getUsers().containsKey(username));
        //System.out.println(getUsers().get(username));
        return getUsers().get(username);
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

    public void editUser(User user) {

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
}
