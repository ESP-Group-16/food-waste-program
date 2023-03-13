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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private Button btnAddItem, btnFinishShop;
    private TextView txtItemCounter;
    private RecyclerView recViewFridgeItems, recViewFreezerItems, recViewCupboardItems;
    private ArrayList<Stock> fridgeItemsList, freezerItemsList, cupboardItemsList;
    private ShoppingListAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        initViews(view);

        // TODO: get the lists from DB
        fridgeItemsList = new ArrayList<>();
        freezerItemsList = new ArrayList<>();
        cupboardItemsList = new ArrayList<>();


        btnFinishShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: navigate user to SelectedItemsFragment
                // navigate user to recipe list fragment TODO: pass correct list to display to fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityShoppingListFragmentContainer, new SelectedItemsFragment());
                transaction.commit();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: display dialog to add a new item to shopping list
                // open add ingredient dialog
                AddShoppingListItemDialog dialog = new AddShoppingListItemDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "add shopping list item");
            }
        });

        // TODO: set adapters for recycler views
        fridgeAdapter = new ShoppingListAdapter(getActivity());
        Stock newItem = new Stock();
        Food foodItem = new Food();
        foodItem.setName("Cheese");
        newItem.setFood(foodItem);
        newItem.setQuantity(2);
        fridgeItemsList.add(newItem);

        fridgeAdapter.setItems(fridgeItemsList);

        recViewFridgeItems.setAdapter(fridgeAdapter);
        recViewFridgeItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        // TODO: set text of counter text
        int itemNumber = fridgeItemsList.size() + freezerItemsList.size() + cupboardItemsList.size();
        txtItemCounter.setText(itemNumber + " items");

        return view;
    }

    private void initViews(View view) {
        btnAddItem = view.findViewById(R.id.fragmentShoppingListAddItem);
        btnFinishShop = view.findViewById(R.id.fragmentShoppingListFinishButton);
        txtItemCounter = view.findViewById(R.id.fragmentShoppingListQuantityTxt);
        recViewCupboardItems = view.findViewById(R.id.fragmentShoppingListCupboardRecyclerView);
        recViewFreezerItems = view.findViewById(R.id.fragmentShoppingListFreezerRecyclerView);
        recViewFridgeItems = view.findViewById(R.id.fragmentShoppingListFridgeRecyclerView);
    }
}
