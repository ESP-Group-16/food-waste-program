package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SearchFragment extends Fragment {

    private RecipeAdapter adapter;
    private Button btnAsian, btnSoup;
    private EditText searchEdtTxt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews(view);

        btnAsian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipePageByCuisine("Asian");
            }
        });

        btnSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipePageByCuisine("Soup");
            }
        });

        // set adapter for list

        return view;
    }

    private void initViews(View view) {
        btnSoup = view.findViewById(R.id.fragmentSearchSoupCuisineButton);
        btnAsian = view.findViewById(R.id.fragmentSearchAsianCuisineButton);
    }

    public void openRecipePageByCuisine(String cuisine) {
        // show PantryFragment TODO: send cuisine over to RecipeListFragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityRecipeFragmentContainer, new RecipeListFragment());
        transaction.commit();
    }
}
