package com.example.espproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.espproject.databinding.FragmentPantryBinding;

public class PantryFragment extends Fragment {

    // TODO: declare UI components as fields
    private FragmentPantryBinding binding;
    private Pantry pantry;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPantryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PantryFragment.this)
                        .navigate(R.id.action_pantryFragment_to_pantryUsersDialog);
            }
        });
        binding.buttonAddingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PantryFragment.this)
                        .navigate(R.id.action_pantryFragment_to_addIngredientDialog);
            }
        });
    }




}
