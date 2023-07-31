package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeMenuFragment extends Fragment {

    /**
     * key to the list to be displayed by RecipeListFragment
     * <p> "favourites" - display favourite recipes </p>
     * <p> "pantry_recipes" - display recipes with ingredients in pantry </p>
     * <p> null - otherwise (e.g. this is not the calling fragment, like when searching) </p>
     */
    public static final String RECIPE_MODE = "recipe_mode";
    private Button btnMyRecipes, btnPantryRecipes, btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_menu, container, false);

        initViews(view);

        btnMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to RecipeListFragment and pass "favourites" as message
                Bundle bundle = new Bundle();
                bundle.putString(RECIPE_MODE, "favourites");
                bundle.putString("back_fragment", "recipe_menu_fragment");
                RecipeListFragment fragment = new RecipeListFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                transaction.commit();
            }
        });

        btnPantryRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to RecipeListFragment and pass "pantry_recipes" as message
                Bundle bundle = new Bundle();
                bundle.putString(RECIPE_MODE, "pantry_recipes");
                bundle.putString("back_fragment", "recipe_menu_fragment");
                RecipeListFragment receiverFragment = new RecipeListFragment();
                receiverFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, receiverFragment);
                transaction.commit();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to SearchFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, new SearchFragment());
                transaction.commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        btnMyRecipes = view.findViewById(R.id.fragmentRecipeMenuMyRecipesButton);
        btnPantryRecipes = view.findViewById(R.id.fragmentRecipeMenuPantryRecipesButton);
        btnSearch = view.findViewById(R.id.fragmentRecipeMenuSearchButton);
    }

}
