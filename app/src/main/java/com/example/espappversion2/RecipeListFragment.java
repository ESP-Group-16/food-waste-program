package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    private TextView txtSearchResult;
    private RecyclerView recipeRecView;
    private RecipeAdapter adapter;
    private ArrayList<Recipe> recipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        initViews(view);

        // TODO: get search result from previous fragment
        String searchResult = "";
        txtSearchResult.setText("Search results for " + searchResult + ":");

        // TODO: get list of recipes to display based on search result
        recipes = new ArrayList<>();

        // set adapter for recycler view
        adapter = new RecipeAdapter(getActivity());

        Recipe recipe = new Recipe();
        recipe.setName("Spaghetti Bolognese");
        recipes.add(recipe);

        adapter.setItems(recipes);

        recipeRecView.setAdapter(adapter);
        recipeRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initViews(View view) {
        txtSearchResult = view.findViewById(R.id.fragmentRecipeListSearchResultTxt);
        recipeRecView = view.findViewById(R.id.fragmentRecipeListRecyclerView);
    }
}
