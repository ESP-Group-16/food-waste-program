package com.example.espproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddIngredientDialog extends DialogFragment {

    // callback interface for passing data back to PantryActivity
    public interface AddItem {
        void onAddItem(Stock stock);
    }

    private AddItem addItem;

    // TODO: declare UI components as fields
    private Button btnAddIngredient;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_ingredient, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: get details of the food item from UI components and set them as the fields of newItem
                // TODO: make sure all the inputs are valid values with checks
                Stock newItem = new Stock();



                // this should be executed when all the values are valid and have been set as fields of newItem
                // send the new item back to PantryActivity
                try {
                    addItem = (AddItem) getActivity();
                    addItem.onAddItem(newItem);
                    dismiss();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        });

        return builder.create();
    }

    // TODO: finish (using the view.findViewById(R.layout.<insert components id>) method)
    // method to initialize UI components
    public void initViews(View view) {
        //btnAddIngredient = view.findViewById(R.id.btnAddIngredient);
    }
}
