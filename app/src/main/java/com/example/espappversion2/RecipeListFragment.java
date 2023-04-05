package com.example.espappversion2;

import static com.example.espappversion2.RecipeMenuFragment.RECIPE_MODE;

import android.icu.util.ICUUncheckedIOException;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeListFragment extends Fragment implements VolleyCallback {


    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {
        recipes = Recipe.generateRecipesGivenJSON(response);

        // set adapter for recycler view to display recipes
        adapter = new RecipeAdapter(getActivity());
        adapter.setItems(recipes);
        recipeRecView.setAdapter(adapter);
        recipeRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onFailure(VolleyError error) {
        Toast.makeText(getActivity(), "Volley error", Toast.LENGTH_SHORT).show();
        System.out.println(error.getMessage());
        System.out.println(Arrays.toString(error.getStackTrace()));
    }

    private TextView txtTitle;
    private Button btnBack;
    private RecyclerView recipeRecView;
    private RecipeAdapter adapter;
    private ArrayList<Recipe> recipes;
    private RecipeAPI recipeAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        initViews(view);
        recipeAPI = new RecipeAPI(getActivity());
        recipes = new ArrayList<>();

        // get the mode and the corresponding list of recipes
        Bundle bundle = getArguments();
        if(bundle != null) {
            String mode = bundle.getString(RECIPE_MODE);
            if(mode != null) {
                //Toast.makeText(getActivity(), mode, Toast.LENGTH_SHORT).show();
                if(mode.equals("favourites")){
                    // TODO: get favourite recipes list
                    txtTitle.setText("Favourite Recipes");
                } else if(mode.equals("pantry_recipes")) {
                    // TODO: get pantry recipes list
                    txtTitle.setText("Pantry Recipes");
                } else if(mode.equals("search_by_cuisine")) {
                    String cuisine = bundle.getString("cuisine");
                    txtTitle.setText("Search by cuisine: " + cuisine);
                    recipeAPI.getRecipesByCuisine(this, cuisine);
                } else if(mode.equals("search_by_category")) {
                    String category = bundle.getString("category");
                    txtTitle.setText("Search by category: " + category);
                    recipeAPI.getRecipesByCategory(this, category);
                } else if(mode.equals("search")) {
                    String search = bundle.getString("search");
                    txtTitle.setText("Search results for: " + search);
                    recipeAPI.getRecipesByName(this, search);
                } else {
                    //Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            // set back button function based on previous fragment
            String backFragment = bundle.getString("back_fragment");
            if(backFragment != null) {
                if(backFragment.equals("search_fragment")) {
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SearchFragment fragment = new SearchFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else if(backFragment.equals("recipe_menu_fragment")) {
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RecipeMenuFragment fragment = new RecipeMenuFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                            transaction.commit();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Cannot set back button", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Back button set to " + backFragment, Toast.LENGTH_SHORT).show();
            }
        }

        return view;
    }

    private void initViews(View view) {
        txtTitle = view.findViewById(R.id.fragmentRecipeListTitleTxt);
        recipeRecView = view.findViewById(R.id.fragmentRecipeListRecyclerView);
        btnBack = view.findViewById(R.id.fragmentRecipeListBackBtn);
    }
}
