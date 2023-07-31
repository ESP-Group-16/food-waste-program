package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class PantryFragment extends Fragment{

    private Button btnAddIngredient;
    private RecyclerView recViewFridge, recViewFreezer, recViewCupboard;

    private PantryItemsAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;

    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        currentUser = Utils.getInstance(getActivity()).getCurrentUser();
        System.out.println("Pantry of current user: " + new Gson().toJson(currentUser.getPantry()));

        initViews(view);
        setUpRecyclerViews();

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add ingredient dialog
                AddPantryItemDialog dialog = new AddPantryItemDialog();
                dialog.show(requireActivity().getSupportFragmentManager(), "add ingredient");
            }
        });

        btnAddIngredient.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("All users:\n" + Utils.getInstance(getActivity()).getUsers());
                //System.out.println(Utils.getInstance(MainActivity.this).getUsers().getClass().getName());
                System.out.println("Current user: " + Utils.getInstance(getActivity()).getCurrentUser());
                return false;
            }
        });

        return view;
    }

    private void initViews(View view) {
        recViewCupboard = view.findViewById(R.id.pantryRecyclerCupboard);
        recViewFridge = view.findViewById(R.id.pantryRecyclerFridge);
        recViewFreezer = view.findViewById(R.id.pantryRecyclerFreezer);
        btnAddIngredient = view.findViewById(R.id.addIngredientButton);
    }

    private void setUpRecyclerViews() {
        // extract items from local storage
        ArrayList<Stock> fridgePantryItems = Utils.getInstance(getActivity()).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[0]);
        System.out.println("Fridge items in PantryFragment: " + fridgePantryItems);
        ArrayList<Stock> freezerPantryItems = Utils.getInstance(getActivity()).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[1]);
        ArrayList<Stock> cupboardPantryItems = Utils.getInstance(getActivity()).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[2]);

        // set adapter for fridge
        fridgeAdapter = new PantryItemsAdapter(getActivity(), Pantry.STORAGE_LOCATIONS[0]);
        fridgeAdapter.setItems(fridgePantryItems);
        recViewFridge.setAdapter(fridgeAdapter);
        recViewFridge.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter for freezer
        freezerAdapter = new PantryItemsAdapter(getActivity(), Pantry.STORAGE_LOCATIONS[1]);
        freezerAdapter.setItems(freezerPantryItems);
        recViewFreezer.setAdapter(freezerAdapter);
        recViewFreezer.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter for cupboard
        cupboardAdapter = new PantryItemsAdapter(getActivity(), Pantry.STORAGE_LOCATIONS[2]);
        cupboardAdapter.setItems(cupboardPantryItems);
        recViewCupboard.setAdapter(cupboardAdapter);
        recViewCupboard.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
