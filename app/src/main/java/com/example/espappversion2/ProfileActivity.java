package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity implements AddAllergyDialog.AddAllergyDialogListener {

    private Button btnLogOut;
    private TextView txtTitle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_profile);

        initViews();
        initBottomNavBar();

        txtTitle.setText("Welcome, " + Utils.getInstance(this).getCurrentUser().getUserName() + "!");
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ask for confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this).setTitle("Log out?")
                        .setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logOut();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
                builder.create().show();
            }
        });

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

    public void logOut() {
        Utils.getInstance(this).clearCurrentUser();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.activityProfileBottomNavBar);
        txtTitle = findViewById(R.id.activityProfileTopTextUsername);
        btnLogOut = findViewById(R.id.activityProfileLogoutButton);
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

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}