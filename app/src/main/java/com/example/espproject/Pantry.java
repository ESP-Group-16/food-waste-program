package com.example.espproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class Pantry {

    private String TAG = "PantryActivity";

    public static final String fireStoreCollectionName = "pantries";

    private int id;
    private String name;

    public Pantry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pantry retrievePantry() {
        //TODO: move this to the interface method for future use
        //TODO: get a user in the future

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return null;
    }

    public void addPantryToDB(Pantry pantry) {
        // check if pantry already exists

        // add pantry to DB if not

    }

    public void updatePantryInDB(Pantry pantry) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, Pantry> pantryMap = new HashMap<>();
        pantryMap.put(pantry.getName(), pantry);

        db.collection("pantries")
                .add(pantryMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
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
