package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity implements AddAllergyDialog.AddAllergyDialogListener {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        initBottomNavBar();

        Button allergyProfileButton = (Button) findViewById(R.id.activityProfileAllergiesButton); // Could be in initviews but is here for simplicity of viewing.
        allergyProfileButton.setOnClickListener(view -> { // Lambda does same as View.OnClickListener
            openAllergyDialog(); // calls method below
        });
        Button preferencesProfileButton = (Button) findViewById(R.id.activityProfilePreferencesButton); // Could be in initviews but is here for simplicity of viewing.
        preferencesProfileButton.setOnClickListener(view -> {
            // TODO: Implement Preferences Button.

        });
    }

    public void openAllergyDialog() { // Shows the Allergy Dialog upon 'Allergies' button click.
        AddAllergyDialog addAllergyDialog = new AddAllergyDialog();
        addAllergyDialog.show(getSupportFragmentManager(), "add or remove allergy dialog");
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.activityProfileBottomNavBar);
    }

    @Override
    public void applyAllergyChanges(String allergy, boolean adremBool) { // Runs the allergy dialog calculations
        // DO CALCULATIONS HERE TO CHANGE THE DATA IN THE DATASTORE
        Repository repo = new Repository(this);
        if (adremBool){ // Add allergy to store
            repo.addAllergy(allergy);
        }
        else{ // remove allergy to store
            repo.removeAllergy(allergy);
        }
//        repo.viewAllergyList();
    }

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