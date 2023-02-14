package com.example.espproject;

import java.util.ArrayList;

public class User {
    private int userId;
    private String username;

    public static void signUp() {

    }

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
