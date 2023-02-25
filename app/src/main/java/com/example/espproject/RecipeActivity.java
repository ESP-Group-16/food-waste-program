package com.example.espproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

// contains RecipeMenuFragment, SearchFragment, RecipeListFragment and RecipeFragment
public class RecipeActivity extends AppCompatActivity {

    private FrameLayout container;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        initViews();
        initBottomNavBar();

        // display RecipeMenuFragment

    }

    private void initViews() {
        container = findViewById(R.id.activityRecipeFragmentContainer);
        bottomNavigationView = findViewById(R.id.activityRecipeBottomNavBar);
    }

    private void initBottomNavBar() {
        bottomNavigationView.setSelectedItemId(R.id.pantry);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.recipe:
                        // do nothing
                        break;
                    case R.id.profile:
                        // navigate user to ProfileActivity and clear backstack
                        Intent profileIntent = new Intent(RecipeActivity.this, ProfileActivity.class);
                        profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(profileIntent);
                        break;
                    case R.id.pantry:
                        // navigate user to RecipeActivity and clear backstack
                        Intent recipeIntent = new Intent(RecipeActivity.this, PantryActivity.class);
                        recipeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(recipeIntent);
                        break;
                    case R.id.shoppingList:
                        // navigate user to ShoppingListActivity and clear backstack
                        Intent shoppingListIntent = new Intent(RecipeActivity.this, ShoppingListActivity.class);
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