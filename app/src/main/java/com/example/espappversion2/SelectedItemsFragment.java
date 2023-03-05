package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedItemsFragment extends Fragment {

    private RecyclerView recViewSelectedItems;
    private Button btnAddItemsToPantry;
    private ArrayList<Stock> items;
    private TextView txtUnitNumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_items, container, false);

        initViews(view);

        btnAddItemsToPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: add items to pantry and return to shopping list
            }
        });

        // TODO: set adapter for list items


        return view;
    }

    private void initViews(View view) {
        recViewSelectedItems = view.findViewById(R.id.fragmentSelectedItemsRecyclerView);
        txtUnitNumber = view.findViewById(R.id.fragmentSelectedItemsTitleTxt);
        btnAddItemsToPantry = view.findViewById(R.id.fragmentSelectedItemsAddToPantryButton);
    }
}