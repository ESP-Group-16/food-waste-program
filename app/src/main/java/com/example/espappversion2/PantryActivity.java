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

import java.util.ArrayList;

public class PantryActivity extends AppCompatActivity implements AddPantryItemDialog.AddItem {

    public interface UpdatePantryItems {
        void onUpdatePantryItems(ArrayList<Stock> pantryItems);
    }

    private FrameLayout container;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<Stock> pantryItems;
    private PantryFragment pantryFragment;

    @Override
    public void onAddItem(Stock stock) {
        // TODO: add item to DB
        Toast.makeText(this, "Item added to pantry: " + stock.getFood().getName(), Toast.LENGTH_SHORT).show();
        pantryItems.add(stock);
        updatePantryItems();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        initViews();
        initBottomNavBar();

        // TODO: check if user has a pantry and display fragment accordingly
        // show PantryFragment
        pantryFragment = new PantryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityPantryFragmentContainer, pantryFragment);
        transaction.commit();

        // TODO: get the list of pantry items from DB and send them to PantryFragment
        pantryItems = getPantryItemsFromDB();
        //  updatePantryItems();
    }

    private void updatePantryItems() {
        ((UpdatePantryItems) pantryFragment).onUpdatePantryItems(pantryItems);
    }

    private ArrayList<Stock> getPantryItemsFromDB() {
        return new ArrayList<>();
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