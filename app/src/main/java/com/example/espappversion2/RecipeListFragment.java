package com.example.espappversion2;

import static android.content.ContentValues.TAG;

import static com.example.espappversion2.RecipeMenuFragment.RECIPE_MODE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RecipeListFragment extends Fragment implements VolleyCallback {

    @Override
    public void onSuccess(JSONObject response, String resultFor) {
        // TODO: map response to recipes
        System.out.println(response);
        //HashMap<String, Object> map = new Gson().fromJson(response.toString(), HashMap.class);
    }

    @Override
    public void onFailure(VolleyError error) {
        Toast.makeText(getActivity(), "Volley error", Toast.LENGTH_SHORT).show();
        System.out.println(error.getMessage());
        System.out.println(Arrays.toString(error.getStackTrace()));
    }

    private TextView txtSearchResult;
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
                if(mode.equals("favourites")){
                    // TODO: get favourite recipes list
                } else if(mode.equals("pantry_recipes")) {
                    // TODO: get pantry recipes list
                } else if(mode.equals("search_by_cuisine")) {
                    // TODO: get recipes corresponding with the search
                    String category = bundle.getString("cuisine");

                } else if(mode.equals("search_by_category")) {
                    // TODO: get recipes corresponding with the category
                    String category = bundle.getString("category");

                } else if(mode.equals("search_by_name")) {
                    // TODO: get recipes corresponding with partial matches of the search
                    String search = bundle.getString("search");

                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        // set adapter for recycler view to display recipes
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
