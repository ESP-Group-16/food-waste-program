package com.example.espappversion2;

import static com.example.espappversion2.RecipeMenuFragment.RECIPE_MODE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeListFragment extends Fragment implements VolleyCallback {


    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {
        if(resultFor.equals("recipe_by_exact_name")) {
            favourites.add(new Recipe(response));
            System.out.println("Favourites when reading in recipes: " + favourites);
            if(favourites.size() == favouriteRecipeNames.size()) {
                System.out.println("Favourites before setting adapter: " + favourites);
                // set adapter for recycler view to display recipes
                adapter = new RecipeAdapter(getActivity(), recipeListMode, extra);
                adapter.setItems(favourites);
                recipeRecView.setAdapter(adapter);
                recipeRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
                System.out.println("Favourites after setting adapter: " + favourites);
            }
        } else if(resultFor.equals("recipe_carbon")) {
            txtCarbonEmission.setText(response.getString("carbon"));
        } else {
            recipes = Recipe.generateRecipesGivenJSON(response);

            // set adapter for recycler view to display recipes
            adapter = new RecipeAdapter(getActivity(), recipeListMode, extra);
            adapter.setItems(recipes);
            recipeRecView.setAdapter(adapter);
            recipeRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onFailure(VolleyError error) {
        Toast.makeText(getActivity(), "Volley error", Toast.LENGTH_SHORT).show();
        System.out.println(error.getMessage());
        System.out.println(Arrays.toString(error.getStackTrace()));
    }

    private TextView txtTitle, txtCarbonEmission;
    private Button btnBack;
    private RecyclerView recipeRecView;
    private RecipeAdapter adapter;
    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> favourites;
    private ArrayList<String> favouriteRecipeNames;
    private RecipeAPI recipeAPI;
    private String recipeListMode;
    private String extra;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        initViews(view);
        recipeAPI = new RecipeAPI(getActivity());
        recipes = new ArrayList<>();
        System.out.println("Favourite recipes when opening RecipeListFragment: " + new Gson().toJson(Utils.getInstance(getActivity()).getFavouriteRecipes()));

        // get the mode and the corresponding list of recipes
        Bundle bundle = getArguments();
        if(bundle != null) {
            recipeListMode = bundle.getString(RECIPE_MODE);
            if(recipeListMode != null) {
                //Toast.makeText(getActivity(), mode, Toast.LENGTH_SHORT).show();
                if(recipeListMode.equals("favourites")){
                    txtTitle.setText("Favourite Recipes");
                    favouriteRecipeNames = Utils.getInstance(getActivity()).getFavouriteRecipes();
                    favourites = new ArrayList<>();
                    for(String name : favouriteRecipeNames) {
                        recipeAPI.getRecipeByExactName(this, name);
                    }
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RecipeMenuFragment fragment = new RecipeMenuFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else if(recipeListMode.equals("pantry_recipes")) {
                    // TODO: get pantry recipes list
                    txtTitle.setText("Pantry Recipes");
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RecipeMenuFragment fragment = new RecipeMenuFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else if(recipeListMode.equals("search_by_cuisine")) {
                    String cuisine = bundle.getString("cuisine");
                    extra = cuisine;
                    txtTitle.setText("Search by cuisine: " + cuisine);
                    recipeAPI.getRecipesByCuisine(this, cuisine);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SearchFragment fragment = new SearchFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else if(recipeListMode.equals("search_by_category")) {
                    String category = bundle.getString("category");
                    extra = category;
                    txtTitle.setText("Search by category: " + category);
                    recipeAPI.getRecipesByCategory(this, category);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SearchFragment fragment = new SearchFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else if(recipeListMode.equals("search")) {
                    String search = bundle.getString("search");
                    extra = search;
                    txtTitle.setText("Search results for: " + search);
                    recipeAPI.getRecipesByName(this, search);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SearchFragment fragment = new SearchFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else {
                    //Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return view;
    }

    private void initViews(View view) {
        txtTitle = view.findViewById(R.id.fragmentRecipeListTitleTxt);
        txtCarbonEmission = view.findViewById(R.id.fragmentRecipeCarbonEmission);
        recipeRecView = view.findViewById(R.id.fragmentRecipeListRecyclerView);
        btnBack = view.findViewById(R.id.fragmentRecipeListBackBtn);
    }
}
