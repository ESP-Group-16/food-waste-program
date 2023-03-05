package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        initViews();
        initBottomNavBar();

        // display RecipeMenuFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityRecipeFragmentContainer, new RecipeMenuFragment());
        transaction.commit();
    }

    private ArrayList<Recipe> getDemoRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Recipe>>(){}.getType();

        User user1 = new User();
        user1.setUserName("Bob");

        ArrayList<String> steps = new ArrayList<>();

        Recipe recipe = new Recipe(1, "Spaghetti Bolognese", "https://food-images.files.bbci.co.uk/food/recipes/easy_spaghetti_bolognese_93639_16x9.jpg",
                10, new ArrayList<String>(), new ArrayList<Ingredient>(), new ArrayList<String>(), new ArrayList<>(), user1);
        recipes.add(recipe);

        //recipe = new Recipe(2, "Lasagne", )

        return recipes;
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.activityRecipeBottomNavBar);
        container = findViewById(R.id.activityRecipeFragmentContainer);
    }

    private void initBottomNavBar() {
        bottomNavigationView.setSelectedItemId(R.id.recipe);
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
                        // navigate user to PantryActivity and clear backstack
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