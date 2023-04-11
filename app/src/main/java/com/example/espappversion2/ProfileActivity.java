package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity implements VolleyCallback {

    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {
        // get list of all allergies and pass it to recycler view
        ingredients = RecipeAPI.convertJSONIngredientsToArrList(response);
        Collections.sort(ingredients);
        adapter.setList(ingredients);
        recViewAllergies.setAdapter(adapter);
        recViewAllergies.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFailure(VolleyError error) {

    }

    private Button btnLogOut;
    private TextView txtTitle;
    private EditText edtTxtFilter;
    private RecyclerView recViewAllergies;
    private BottomNavigationView bottomNavigationView;

    private RecipeAPI recipeAPI;
    private AllergyItemAdapter adapter;
    private ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_profile);
        recipeAPI = new RecipeAPI(this);
        recipeAPI.getAllIngredients(this);
        adapter = new AllergyItemAdapter(this);
        ingredients = new ArrayList<>();

        initViews();
        initBottomNavBar();

        txtTitle.setText(Utils.getInstance(this).getCurrentUser().getUserName() + "'s Profile");

        edtTxtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // filter ingredients
                String search = edtTxtFilter.getText().toString();
                ArrayList<String> newList = new ArrayList<>();
                for(String s : ingredients) {
                    if(s.toLowerCase().contains(search.toLowerCase())) {
                        newList.add(s);
                    }
                }
                adapter.setList(newList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
        recViewAllergies = findViewById(R.id.activityProfileAllergiesRecView);
        edtTxtFilter = findViewById(R.id.activityProfileSearchBarEdtTxt);
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
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}