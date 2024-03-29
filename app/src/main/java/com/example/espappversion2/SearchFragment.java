package com.example.espappversion2;

import static android.content.ContentValues.TAG;

import static com.example.espappversion2.RecipeMenuFragment.RECIPE_MODE;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment implements VolleyCallback {

    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {
        if(resultFor.equals("cuisines")) {
            cuisines = recipeAPI.convertJSONCuisinesToArrList(response);
        } else if(resultFor.equals("categories")) {
            categories = recipeAPI.convertJSONCategoriesToArrList(response);
            System.out.println(categories);
        }
        // set adapter for lists
        categoryAdapter.setList(categories);
        cuisineAdapter.setList(cuisines);
        recViewCategories.setAdapter(categoryAdapter);
        recViewCuisines.setAdapter(cuisineAdapter);
        recViewCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
        recViewCuisines.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onFailure(VolleyError error) {

    }

    private RecyclerView recViewCategories, recViewCuisines;
    private Button btnSearch, btnBack;
    private EditText searchEdtTxt;

    private CategoryCuisineAdapter categoryAdapter, cuisineAdapter;
    private RecipeAPI recipeAPI;
    private ArrayList<String> categories, cuisines = new ArrayList<>();
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews(view);
        recipeAPI = new RecipeAPI(getActivity());
        recipeAPI.getAllCategories(this);
        recipeAPI.getAllCuisines(this);

        categoryAdapter = new CategoryCuisineAdapter("category", getActivity());
        cuisineAdapter = new CategoryCuisineAdapter("cuisine", getActivity());
        categories = new ArrayList<>();
        cuisines = new ArrayList<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = searchEdtTxt.getText().toString();
                if(!input.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(RECIPE_MODE, "search");
                    bundle.putString("search", input);
                    bundle.putString("back_fragment", "search_fragment");
                    RecipeListFragment receiverFragment = new RecipeListFragment();
                    receiverFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.activityRecipeFragmentContainer, receiverFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Please enter something to search for", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to RecipeMenuFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, new RecipeMenuFragment());
                transaction.commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        recViewCategories = view.findViewById(R.id.fragmentSearchByCategoryRecView);
        recViewCuisines = view.findViewById(R.id.fragmentSearchByCuisineRecView);
        btnSearch = view.findViewById(R.id.fragmentSearchButton);
        btnBack = view.findViewById(R.id.fragmentSearchBackButton);
        searchEdtTxt = view.findViewById(R.id.fragmentSearchBarEdtTxt);
    }
}
