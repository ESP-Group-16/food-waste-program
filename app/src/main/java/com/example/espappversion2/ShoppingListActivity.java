package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ShoppingListActivity extends AppCompatActivity implements AddShoppingListItemDialog.AddShoppingListItem {

    @Override
    public void onAddShoppingListItem(Stock item, String storageLocation) {
        // add the new item to shopping list in DB
        //Toast.makeText(this, "New item name: " + item.getFood().getName() + " stored in " + storageLocation, Toast.LENGTH_SHORT).show();

        int i = (storageLocation.equals("fridge"))? 0 : (storageLocation.equals("freezer")? 1 : (storageLocation.equals("cupboard"))? 2 : -1);
        repository.addItemToShoppingList(item, i);

        // restart ShoppingListFragment to display updated list
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityShoppingListFragmentContainer, new ShoppingListFragment());
        transaction.commit();
    }

    private BottomNavigationView bottomNavigationView;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        initViews();
        initBottomNavBar();

        repository = new Repository(this);

        // display ShoppingListFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityShoppingListFragmentContainer, new ShoppingListFragment());
        transaction.commit();
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.activityShoppingListBottomNavBar);
    }

    private void initBottomNavBar() {
        bottomNavigationView.setSelectedItemId(R.id.shoppingList);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.shoppingList:
                        // do nothing
                        break;
                    case R.id.profile:
                        // navigate user to ProfileActivity and clear backstack
                        Intent profileIntent = new Intent(ShoppingListActivity.this, ProfileActivity.class);
                        profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(profileIntent);
                        break;
                    case R.id.recipe:
                        // navigate user to RecipeActivity and clear backstack
                        Intent recipeIntent = new Intent(ShoppingListActivity.this, RecipeActivity.class);
                        recipeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(recipeIntent);
                        break;
                    case R.id.pantry:
                        // navigate user to PantryActivity and clear backstack
                        Intent shoppingListIntent = new Intent(ShoppingListActivity.this, PantryActivity.class);
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