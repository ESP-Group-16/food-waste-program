package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

    private Repository repository;
    private ArrayList<ArrayList<Stock>> shoppingList;
    private ArrayList<Stock> fridgeItemsList, freezerItemsList, cupboardItemsList;
    private ShoppingListAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        initViews(view);
        repository = new Repository();

        // get the lists from DB
        shoppingList = repository.getShoppingList();
        fridgeItemsList = shoppingList.get(0);
        freezerItemsList = shoppingList.get(1);
        cupboardItemsList = shoppingList.get(2);

//        // some initial data
//        Stock newItem = new Stock();
//        Food foodItem = new Food();
//        foodItem.setName("Cheese");
//        newItem.setFood(foodItem);
//        newItem.setQuantity(2);
//        fridgeItemsList.add(newItem);

        // TODO: extract this into a general method
        // fridge
        setAdapter(fridgeAdapter, fridgeItemsList, recViewFridgeItems, 0);

        // freezer
        setAdapter(freezerAdapter, freezerItemsList, recViewFreezerItems, 1);

        // cupboard
        setAdapter(cupboardAdapter, cupboardItemsList, recViewCupboardItems, 2);

        // set text of counter text
        int itemNumber = fridgeItemsList.size() + freezerItemsList.size() + cupboardItemsList.size();
        txtItemCounter.setText(itemNumber + " items");

        btnFinishShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ask for verification before proceeding
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle("Finish shop?")
                        .setMessage("Finish the shop with selected items")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // TODO: navigate user to SelectedItemsFragment
                                // navigate user to recipe list fragment TODO: pass correct list to display to fragment
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.activityShoppingListFragmentContainer, new SelectedItemsFragment());
                                transaction.commit();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // don't do anything
                            }
                        });
                builder.create().show();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add ingredient dialog
                AddShoppingListItemDialog dialog = new AddShoppingListItemDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "add shopping list item");
            }
        });

        return view;
    }

    private void setAdapter(ShoppingListAdapter adapter, ArrayList<Stock> list, RecyclerView recyclerView, int i) {
        adapter = new ShoppingListAdapter(getActivity(), i);
        adapter.setItems(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
