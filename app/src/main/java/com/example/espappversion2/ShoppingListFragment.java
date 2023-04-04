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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingListFragment extends Fragment {

    private Button btnAddItem, btnFinishShop;
    private TextView txtItemCounter;
    private RecyclerView recViewFridgeItems, recViewFreezerItems, recViewCupboardItems;

    private Repository repository;
    private HashMap<String, ArrayList<Stock>> shoppingList;
    private ArrayList<Stock> fridgeItemsList, freezerItemsList, cupboardItemsList;
    private ShoppingListAdapter fridgeAdapter, freezerAdapter, cupboardAdapter;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        initViews(view);
        currentUser = Utils.getInstance(getActivity()).getCurrentUser();
        //repository = new Repository(getActivity());

        // get the lists from DB
//        shoppingList = repository.getShoppingList();
//        fridgeItemsList = shoppingList.get(0);
//        freezerItemsList = shoppingList.get(1);
//        cupboardItemsList = shoppingList.get(2);

        // get the lists from DB
        shoppingList = currentUser.getShoppingList();
        fridgeItemsList = shoppingList.get(Pantry.STORAGE_LOCATIONS[0]);
        freezerItemsList = shoppingList.get(Pantry.STORAGE_LOCATIONS[1]);
        cupboardItemsList = shoppingList.get(Pantry.STORAGE_LOCATIONS[2]);


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

        btnAddItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("Shopping list: " + Utils.getInstance(getActivity()).getShoppingListByUser(currentUser));
                return false;
            }
        });

        btnFinishShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if shopping list is empty
                if(!Utils.getInstance(getActivity()).isShoppingListEmpty(currentUser)) {
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
//                                        String allItems = "";
//                                        for (Stock s : selectedItems) {
//                                            allItems += " " + s.getFood().getName();
//                                        }
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

                                        // add selected items to pantry components
                                        Utils.getInstance(getActivity()).addShoppingListItemsToPantry(Pantry.STORAGE_LOCATIONS[0], fridgeAdapter.getSelectedItems());
                                        Utils.getInstance(getActivity()).addShoppingListItemsToPantry(Pantry.STORAGE_LOCATIONS[1], freezerAdapter.getSelectedItems());
                                        Utils.getInstance(getActivity()).addShoppingListItemsToPantry(Pantry.STORAGE_LOCATIONS[2], cupboardAdapter.getSelectedItems());

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
