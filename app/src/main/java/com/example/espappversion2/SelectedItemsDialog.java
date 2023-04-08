package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SelectedItemsDialog extends DialogFragment {

    interface UpdateShoppingList {
        void onUpdateShoppingList();
    }

    private UpdateShoppingList updateShoppingList;

    private Button btnAdd, btnCancel;
    private RecyclerView recyclerViewItems;

    private SelectedItemsAdapter adapter;
    private ArrayList<Stock> selectedItems;

    public SelectedItemsDialog(ArrayList<Stock> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_selected_items, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        adapter = new SelectedItemsAdapter(getActivity());

        // set adapter
        adapter.setItems(selectedItems);
        recyclerViewItems.setAdapter(adapter);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add items to pantry - ask for confirmation first

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity())
                        .setTitle("Add items to pantry")
                        .setMessage("Do you want to add the selected items to the pantry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // TODO: add items to pantry and remove them from shopping list
                                System.out.println("Fridge items: " + adapter.getFridgeItems());
                                for(Stock stock : adapter.getFridgeItems()) {
                                    Utils.getInstance(getActivity()).addPantryItem(Pantry.STORAGE_LOCATIONS[0], stock);
                                    Utils.getInstance(getActivity()).removeShoppingListItem(Utils.getInstance(getActivity()).getCurrentUser().getShoppingList().indexOf(stock));
                                    //System.out.println(stock + " removed from shopping list and added to pantry!");
                                }
                                for(Stock stock : adapter.getFreezerItems()) {
                                    Utils.getInstance(getActivity()).addPantryItem(Pantry.STORAGE_LOCATIONS[1], stock);
                                    Utils.getInstance(getActivity()).removeShoppingListItem(Utils.getInstance(getActivity()).getCurrentUser().getShoppingList().indexOf(stock));
                                    //System.out.println(stock + " removed from shopping list and added to pantry!");
                                }
                                for(Stock stock : adapter.getCupboardItems()) {
                                    Utils.getInstance(getActivity()).addPantryItem(Pantry.STORAGE_LOCATIONS[2], stock);
                                    Utils.getInstance(getActivity()).removeShoppingListItem(Utils.getInstance(getActivity()).getCurrentUser().getShoppingList().indexOf(stock));
                                    //System.out.println(stock + " removed from shopping list and added to pantry!");
                                }
                                Toast.makeText(getActivity(), "Items added to pantry!", Toast.LENGTH_SHORT).show();

                                try{
                                    updateShoppingList = (UpdateShoppingList) getActivity();
                                    updateShoppingList.onUpdateShoppingList();
                                } catch(ClassCastException e) {
                                    e.printStackTrace();
                                }

                                dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // do nothing
                            }
                        });
                builder1.create().show();
            }
        });

        return builder.create();
    }

    private void initViews(View view) {
        btnAdd = view.findViewById(R.id.dialogSelectedItemsAddButton);
        btnCancel = view.findViewById(R.id.dialogSelectedItemsCancelButton);
        recyclerViewItems = view.findViewById(R.id.dialogSelectedItemsRecyclerView);
    }
}
