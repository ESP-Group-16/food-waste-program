package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity implements AddAllergyDialog.AddAllergy{

    @Override
    public void onAddAllergy(String allergy) { // This is the method called fro AddAllergyDialog when we want to send back data (allergy string for now).
        // Do things - aka add allergy to the hashmap.
    }

    private BottomNavigationView bottomNavigationView;

    // Button for Allergies
    private Button AllergyProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        initBottomNavBar();

        AllergyProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Example:
//                // open add ingredient dialog
//                AddPantryItemDialog dialog = new AddPantryItemDialog();
//                dialog.show(getActivity().getSupportFragmentManager(), "add ingredient");
                AddAllergyDialog dialog = new AddAllergyDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "add ingredient");
            }
        });

    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.activityProfileBottomNavBar);
        AllergyProfileButton = findViewById(R.id.activityProfileAllergiesButton);
    }

    // TODO: Create logic methods for retrieving the hashmap of allergies from the user and adding the allergies


    private void initBottomNavBar() {
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.profile:
                        // do nothing
                        break;
                    case R.id.pantry:
                        // navigate user to PantryActivity and clear backstack
                        Intent profileIntent = new Intent(ProfileActivity.this, PantryActivity.class);
                        profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(profileIntent);
                        break;
                    case R.id.recipe:
                        // navigate user to RecipeActivity and clear backstack
                        Intent recipeIntent = new Intent(ProfileActivity.this, RecipeActivity.class);
                        recipeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(recipeIntent);
                        break;
                    case R.id.shoppingList:
                        // navigate user to ShoppingListActivity and clear backstack
                        Intent shoppingListIntent = new Intent(ProfileActivity.this, ShoppingListActivity.class);
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