package com.example.espappversion2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryFragment extends Fragment implements PantryActivity.UpdatePantryItems {

    private Button btnUsers, btnAddIngredient;
    private RecyclerView recViewFridge, recViewFreezer, recViewCupboard;
    private PantryItemsAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;
    private ArrayList<Stock> pantryItems = new ArrayList<>();

    @Override
    public void onUpdatePantryItems(ArrayList<Stock> pantryItems) {
        this.pantryItems = pantryItems;

//        fridgeAdapter.setItems(pantryItems); // # TODO: Commented out

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);

        initViews(view);

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open users dialog
                new AlertDialog.Builder(getActivity()).setTitle("List of Users")
                        .setMessage("This is where the users are displayed")
                        .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create().show();
            }
        });

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add ingredient dialog
                AddPantryItemDialog dialog = new AddPantryItemDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "add ingredient");
            }
        });

        // TODO: separate pantry items based on storage location


        // set adapter for fridge
        fridgeAdapter = new PantryItemsAdapter(getActivity());
        pantryItems.add(new Stock());

        fridgeAdapter.setItems(pantryItems); // # TODO: Commented out to test.

        recViewFridge.setAdapter(fridgeAdapter);
        recViewFridge.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set adapter for freezer
        freezerAdapter = new PantryItemsAdapter(getActivity());

        // set adapter for cupboard

        return view;
    }

    private void initViews(View view) {
        recViewCupboard = view.findViewById(R.id.pantryRecyclerCupboard);
        recViewFridge = view.findViewById(R.id.pantryRecyclerFridge);
        recViewFreezer = view.findViewById(R.id.pantryRecyclerFreezer);

        btnUsers = view.findViewById(R.id.pantryUsersButton);
        btnAddIngredient = view.findViewById(R.id.addIngredientButton);
    }
}
