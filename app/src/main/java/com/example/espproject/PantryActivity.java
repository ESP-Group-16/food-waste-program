package com.example.espproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

public class PantryActivity extends AppCompatActivity implements AddIngredientDialog.AddItem, CreatePantryDialog.CreateNewPantry {

    // CreatePantryDialog passes the name of the new pantry using this method
    @Override
    public void onCreateNewPantry(String name) {
        // TODO: create the new pantry and display the PantryFragment
    }

    // AddIngredientsDialog passes the new item to be added to the pantry using this method
    @Override
    public void onAddItem(Stock stock) {
        // TODO: add new item to pantry and update UI components
    }

    // TODO: declare UI components as fields



    private FirebaseFirestore firebaseFirestore;
    private Pantry pantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        //TODO: review where to initialize this in the future
        firebaseFirestore = FirebaseFirestore.getInstance();

        //retrieve pantry from the database
        pantry = pantry.retrievePantry(firebaseFirestore);

        // TODO: check if user has a pantry, start PantryFragment or NoPantryFragment accordingly


    }
}