package com.example.espappversion2;

import android.app.AlertDialog;
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
    private RecyclerView recViewFridgeItems;

    private Repository repository;
    private ArrayList<Stock> shoppingList;
    private ShoppingListAdapter adapter;
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


        // setting the adapter for shopping list
        adapter = new ShoppingListAdapter(getActivity(), 0);
        adapter.setItems(shoppingList);
        recViewFridgeItems.setAdapter(adapter);
        recViewFridgeItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        // set text of counter text
        int itemNumber = shoppingList.size();
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
                    ArrayList<Stock> selectedItems = adapter.getSelectedItems();
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
                                        String selectedItemsString = "";
                                        for (Stock s : adapter.getSelectedItems()) {
                                            selectedItemsString += " " + s.getFood().getName();
                                        }

                                        // TODO: add the shopping list items to the correct storage compartment
                                        // add selected items to pantry components
                                        Utils.getInstance(getActivity()).addShoppingListItemsToPantry(Pantry.STORAGE_LOCATIONS[0], adapter.getSelectedItems());

                                        AlertDialog.Builder selectedItemsDialog = new AlertDialog.Builder(getActivity())
                                                .setTitle("Items added to pantry")
                                                .setMessage("Items: " + selectedItemsString)
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
        recViewFridgeItems = view.findViewById(R.id.fragmentShoppingListFridgeRecyclerView);
    }
}
