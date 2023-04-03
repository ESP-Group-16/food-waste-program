package com.example.espappversion2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class PantryFragment extends Fragment{

    private Button btnAddIngredient;
    private RecyclerView recViewFridge, recViewFreezer, recViewCupboard;

    private PantryItemsAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);

        initViews(view);

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add ingredient dialog
                AddPantryItemDialog dialog = new AddPantryItemDialog();
                dialog.show(requireActivity().getSupportFragmentManager(), "add ingredient");
            }
        });

        // Step 1: EXTRACT ITEMS FROM DATASOURCE.
        ArrayList<Stock> cupboardPantryItems = Datasource.getInstance().PantryInformation.get("cupboard");
        ArrayList<Stock> freezerPantryItems = Datasource.getInstance().PantryInformation.get("freezer");
        ArrayList<Stock> fridgePantryItems = Datasource.getInstance().PantryInformation.get("fridge");


        // Step 2: ASSIGN DATA ITEMS TO CREATE NEW RECYCLERVIEW (instanced) ITEMS.
        // set adapter for fridge
        fridgeAdapter = new PantryItemsAdapter(getActivity(), "fridge");

        fridgeAdapter.setItems(fridgePantryItems);

        recViewFridge.setAdapter(fridgeAdapter);
        recViewFridge.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter for freezer
        freezerAdapter = new PantryItemsAdapter(getActivity(), "freezer");

        freezerAdapter.setItems(freezerPantryItems);

        recViewFreezer.setAdapter(freezerAdapter);
        recViewFreezer.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter for cupboard
        cupboardAdapter = new PantryItemsAdapter(getActivity(), "cupboard");

        cupboardAdapter.setItems(cupboardPantryItems);

        recViewCupboard.setAdapter(cupboardAdapter);
        recViewCupboard.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initViews(View view) {
        recViewCupboard = view.findViewById(R.id.pantryRecyclerCupboard);
        recViewFridge = view.findViewById(R.id.pantryRecyclerFridge);
        recViewFreezer = view.findViewById(R.id.pantryRecyclerFreezer);

        btnAddIngredient = view.findViewById(R.id.addIngredientButton);
    }

}
