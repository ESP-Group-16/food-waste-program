package com.example.espproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.espproject.databinding.FragmentShoppinglistBinding;
public class ShoppingListFragment extends Fragment {

    private FragmentShoppinglistBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShoppinglistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.buttonAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ShoppingListFragment.this)
//                        .navigate(R.id.action_shoppingListFragment_to_addIngredientDialog);
//            }
//        });
        binding.buttonFinishshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShoppingListFragment.this)
                        .navigate(R.id.action_shoppingListFragment_to_selectedItemsFragment);
            }
        });
    }
}
