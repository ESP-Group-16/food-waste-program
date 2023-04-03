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

import java.util.ArrayList;

public class RecipeMenuFragment extends Fragment {

    private Button btnMyRecipes, btnPantryRecipes, btnSearch;
    private Repository repo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_menu, container, false);
        repo = new Repository(getActivity());

        initViews(view);

        btnMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to recipe list fragment TODO: pass correct list to display to fragment
                ArrayList<Recipe> outputrecipes= repo.GetFavouriteRecipes();
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipes_key", outputrecipes);
                RecipeListFragment receiverFragment = new RecipeListFragment();
                receiverFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, receiverFragment);
                transaction.commit();
            }
        });

        btnPantryRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to recipe list fragment TODO: pass correct list to display to fragment
                ArrayList<Recipe> outputrecipes= repo.GetAllRecipes();
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipes_key", outputrecipes);
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
                // navigate user to search fragment
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
