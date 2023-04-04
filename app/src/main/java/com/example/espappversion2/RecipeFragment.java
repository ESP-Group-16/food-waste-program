package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeFragment extends Fragment {

    private Button btnBack, btnFavouriteRecipe, btnAddIngredientsToCart;
    private TextView txtRecipeName, txtInstructions;
    private ImageView imgRecipe;
    private RecyclerView recViewIngredients;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        initViews(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user back to RecipeListFragment
                // TODO: go back to the list of search results that was displayed previously
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, new RecipeListFragment());
                transaction.commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        btnBack = view.findViewById(R.id.fragmentRecipeBackBtn);
        btnFavouriteRecipe = view.findViewById(R.id.fragmentRecipeFavouriteBtn);
        btnAddIngredientsToCart = view.findViewById(R.id.fragmentRecipeAddToShopBtn);
        txtRecipeName = view.findViewById(R.id.fragmentRecipeNameTxt);
        txtInstructions = view.findViewById(R.id.fragmentRecipeInstructionsTxt);
        imgRecipe = view.findViewById(R.id.fragmentRecipeImg);
        recViewIngredients = view.findViewById(R.id.fragmentRecipeIngredientsRecycler);
    }
}
