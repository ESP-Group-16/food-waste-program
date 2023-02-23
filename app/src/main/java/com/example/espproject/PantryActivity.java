package com.example.espproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class PantryActivity extends AppCompatActivity implements AddIngredientDialog.AddItem, CreatePantryDialog.CreateNewPantry {
    
    private String TAG = "PantryActivity";
    private FirebaseAuth mAuth;
    private Map<String, Object> pantryMap = new HashMap<>();

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

    private TextView txtPantryText;

    private FirebaseFirestore firebaseFirestore;
    private Pantry pantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        txtPantryText = findViewById(R.id.txtPantryText);

        //TODO: review where to initialize this in the future
        firebaseFirestore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // TODO: check if user has a pantry, start PantryFragment or NoPantryFragment accordingly
        
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        if(email != null) {
            txtPantryText.setText("Hello " + email + "!");
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        //retrieve pantry from the database
        db.collection("pantries")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                pantryMap = document.getData();
                                txtPantryText.setText(pantryMap.get("name").toString());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}