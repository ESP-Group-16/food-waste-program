package com.example.espproject;

import com.google.firebase.firestore.FirebaseFirestore;

public class Pantry {
    public static final String fireStoreCollectionName = "pantries";
    private int id;
    private String name;

    public Pantry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pantry() {

    }

    public Pantry retrievePantry(FirebaseFirestore db) {
        //TODO: move this to the interface method for future use
        //TODO: get a user in the future

        // pass the pantry back to pantry activity
        db.collection(fireStoreCollectionName).document("PsFFlfX7gicMjIyBay5H").get();

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
