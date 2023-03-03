package com.example.espproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.espproject.databinding.FragmentRecipeMenuBinding;
import com.google.firebase.firestore.DocumentSnapshot;

public class RecipeMenuFragment extends Fragment {

    private FragmentRecipeMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecipeMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeMenuFragment.this)
                        .navigate(R.id.action_recipeMenuFragment_to_searchFragment);
            }
        });
        binding.buttonRecipelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeMenuFragment.this)
                        .navigate(R.id.action_recipeMenuFragment_to_recipeListFragment);
            }
        });
        binding.buttonPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecipeMenuFragment.this)
                        .navigate(R.id.action_recipeMenuFragment_to_recipeListFragment);
            }
        });
    }
}





