package com.example.espappversion2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
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

public class RecipeListFragment extends Fragment{

    private TextView txtSearchResult;
    private RecyclerView recipeRecView;
    private RecipeAdapter adapter;
    private ArrayList<Recipe> recipes;
    Repository repo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        initViews(view);
        repo = new Repository(getActivity());
        // once we retrieve search string we search using repo
        Bundle bundle = getArguments();
        recipes = (ArrayList<Recipe>) bundle.getSerializable("recipes_key");
        //Log.d(TAG, recipes.get(0).getImageURL());

        // TODO: get search result from previous fragment
        String searchResult = "";
        txtSearchResult.setText("Search results for " + searchResult + ":");

        // set adapter for recycler view
        adapter = new RecipeAdapter(getActivity());
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
