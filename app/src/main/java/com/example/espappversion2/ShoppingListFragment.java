package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        repository = new Repository(getActivity());

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
        fridgeAdapter = new ShoppingListAdapter(getActivity(), 0);
        fridgeAdapter.setItems(fridgeItemsList);
        recViewFridgeItems.setAdapter(fridgeAdapter);
        recViewFridgeItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        // freezer
        freezerAdapter = new ShoppingListAdapter(getActivity(), 1);
        freezerAdapter.setItems(freezerItemsList);
        recViewFreezerItems.setAdapter(freezerAdapter);
        recViewFreezerItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        // cupboard
        cupboardAdapter = new ShoppingListAdapter(getActivity(), 2);
        cupboardAdapter.setItems(cupboardItemsList);
        recViewCupboardItems.setAdapter(cupboardAdapter);
        recViewCupboardItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set text of counter text
        int itemNumber = fridgeItemsList.size() + freezerItemsList.size() + cupboardItemsList.size();
        txtItemCounter.setText(itemNumber + " items");

        btnFinishShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if shopping list is empty
                if(!repository.isShoppingListEmpty()) {
                    // check if selected items list is empty
                    ArrayList<Stock> selectedItems = new ArrayList<>();
                    selectedItems.addAll(freezerAdapter.getSelectedItems());
                    selectedItems.addAll(fridgeAdapter.getSelectedItems());
                    selectedItems.addAll(cupboardAdapter.getSelectedItems());
                    if (!selectedItems.isEmpty()) {
                        // ask for verification before proceeding
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                                .setTitle("Finish shop?")
                                .setMessage("Do you want to remove the selected items from your shopping list and add them to the pantry?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // a bunch of stuff used for debugging - please ignore
                                        String allItems = "";
                                        for (Stock s : selectedItems) {
                                            allItems += " " + s.getFood().getName();
                                        }
                                        String fridgeItems = "";
                                        for (Stock s : fridgeAdapter.getSelectedItems()) {
                                            fridgeItems += " " + s.getFood().getName();
                                        }
                                        String freezerItems = "";
                                        for (Stock s : freezerAdapter.getSelectedItems()) {
                                            freezerItems += " " + s.getFood().getName();
                                        }
                                        String cupboardItems = "";
                                        for (Stock s : cupboardAdapter.getSelectedItems()) {
                                            cupboardItems += " " + s.getFood().getName();
                                        }
                                        //Toast.makeText(getActivity(), "Selected items size: " + selectedItems.size() + "\n" + allItems, Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(getActivity(), "Fridge selected items: " + fridgeItems, Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(getActivity(), "Freezer selected items: " + freezerItems, Toast.LENGTH_SHORT).show();
//                                        Toast.makeText(getActivity(), "Cupboard selected items: " + cupboardItems, Toast.LENGTH_SHORT).show();

                                        // add items to pantry and remove them from shopping list
                                        for(Stock s : fridgeAdapter.getSelectedItems()) {
                                            repository.addStockItem("fridge", s);
                                            repository.removeItemFromShoppingList(s, 0);
                                        }
                                        for(Stock s : freezerAdapter.getSelectedItems()) {
                                            repository.addStockItem("freezer", s);
                                            repository.removeItemFromShoppingList(s, 1);
                                        }
                                        for(Stock s : cupboardAdapter.getSelectedItems()) {
                                            repository.addStockItem("cupboard", s);
                                            repository.removeItemFromShoppingList(s, 2);
                                        }

                                        AlertDialog.Builder selectedItemsDialog = new AlertDialog.Builder(getActivity())
                                                .setTitle("Items added to pantry")
                                                .setMessage("Fridge: " + fridgeItems + "\nFreezer: " + freezerItems + "\nCupboard: " + cupboardItems)
                                                .setPositiveButton("Go To Pantry", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        // navigate user to Pantry
                                                        Intent shoppingListIntent = new Intent(getActivity(), PantryActivity.class);
                                                        shoppingListIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(shoppingListIntent);
                                                    }
                                                })
                                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        // restart ShoppingListFragment to display updated list
                                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.activityShoppingListFragmentContainer, new ShoppingListFragment());
                                                        transaction.commit();
                                                    }
                                                });
                                        selectedItemsDialog.create().show();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // don't do anything
                                    }
                                });
                        builder.create().show();
                    } else {
                        Toast.makeText(getActivity(), "Please select purchased items!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "The shopping list is empty!", Toast.LENGTH_SHORT).show();
                }
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

    private void initViews(View view) {
        btnAddItem = view.findViewById(R.id.fragmentShoppingListAddItem);
        btnFinishShop = view.findViewById(R.id.fragmentShoppingListFinishButton);
        txtItemCounter = view.findViewById(R.id.fragmentShoppingListQuantityTxt);
        recViewCupboardItems = view.findViewById(R.id.fragmentShoppingListCupboardRecyclerView);
        recViewFreezerItems = view.findViewById(R.id.fragmentShoppingListFreezerRecyclerView);
        recViewFridgeItems = view.findViewById(R.id.fragmentShoppingListFridgeRecyclerView);
    }
}
