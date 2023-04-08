package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    private ArrayList<Stock> shoppingList;
    private ShoppingListAdapter adapter;
    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        initViews(view);
        currentUser = Utils.getInstance(getActivity()).getCurrentUser();

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
                Toast.makeText(getActivity(), "Update list", Toast.LENGTH_SHORT).show();
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

                        String selectedItemsString = "";
                        for (Stock s : adapter.getSelectedItems()) {
                            selectedItemsString += " " + s.getFood().getName();
                        }

                        // open SelectedItemsDialog to display items to be added to the pantry
                        SelectedItemsDialog dialog = new SelectedItemsDialog(selectedItems);

                        dialog.show(getActivity().getSupportFragmentManager(), "selected_items_dialog");

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
