package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PantryActivity extends AppCompatActivity implements AddPantryItemDialog.AddPantryItemDialogListener {

    private FrameLayout container;
    private BottomNavigationView bottomNavigationView;
    private PantryFragment pantryFragment;

    @Override
    public void applyPantryItemChanges(String storageloc, Stock stock) {

        // Comes from AddPantryItemDialog: We need to add pantry item here

        Repository repo = new Repository(this);
        repo.addStockItem(storageloc, stock);
        repo.viewAllPantry();
        //Toast.makeText(this, "New item " + stock.getFood().getName() + " added to " + storageloc, Toast.LENGTH_SHORT).show();
        Utils.getInstance(this).addPantryItem(storageloc, stock);
        //System.out.println("Users before restarting PantryFragment: " + new Gson().toJson(Utils.getInstance(this).getUsers()));

        // restart the fragment to display updated list
        pantryFragment = new PantryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityPantryFragmentContainer, pantryFragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_pantry);

        initViews();
        initBottomNavBar();

        // show PantryFragment
        pantryFragment = new PantryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityPantryFragmentContainer, pantryFragment);
        transaction.commit();
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

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}