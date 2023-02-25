package com.example.espproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

public class PantryActivity extends AppCompatActivity implements AddIngredientDialog.AddItem {

//    // CreatePantryDialog passes the name of the new pantry using this method
//    @Override
//    public void onCreateNewPantry(String name) {
//        // TODO: create the new pantry and display the PantryFragment
//    }

    // AddIngredientsDialog passes the new item to be added to the pantry using this method
    @Override
    public void onAddItem(Stock stock) {
        // TODO: add new item to pantry and update UI components
    }

    // TODO: declare UI components as fields
    private FrameLayout container;
    private BottomNavigationView bottomNavigationView;

    private FirebaseFirestore firebaseFirestore;
    private Pantry pantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        initViews();
        initBottomNavBar();

        // TODO: check if user has a pantry, start PantryFragment or NoPantryFragment accordingly


    }

    private void initViews() {
        container = findViewById(R.id.activityPantryFragmentContainer);
        bottomNavigationView = findViewById(R.id.activityPantryBottomNavBar);
    }

    private void initBottomNavBar() {
        bottomNavigationView.setSelectedItemId(R.id.pantry);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.pantry:
                        // do nothing
                        break;
                    case R.id.profile:
                        // navigate user to ProfileActivity and clear backstack
                        Intent profileIntent = new Intent(PantryActivity.this, ProfileActivity.class);
                        profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(profileIntent);
                        break;
                    case R.id.recipe:
                        // navigate user to RecipeActivity and clear backstack
                        Intent recipeIntent = new Intent(PantryActivity.this, RecipeActivity.class);
                        recipeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(recipeIntent);
                        break;
                    case R.id.shoppingList:
                        // navigate user to ShoppingListActivity and clear backstack
                        Intent shoppingListIntent = new Intent(PantryActivity.this, ShoppingListActivity.class);
                        shoppingListIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(shoppingListIntent);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}