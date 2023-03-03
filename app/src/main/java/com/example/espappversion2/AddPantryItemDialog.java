package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddPantryItemDialog extends DialogFragment {

    // callback interface for passing data back to PantryActivity
    public interface AddItem {
        void onAddItem(Stock stock);
    }

    private AddItem addItem;
    private Button btnAddIngredient;
    private EditText edtTxtIngredientName, edtTxtQuantity;
    private DatePicker datePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_pantry_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: get details of the food item from UI components and set them as the fields of newItem
                // TODO: make sure all the inputs are valid values with checks

                if (edtTxtIngredientName.getText().toString().isEmpty() || edtTxtQuantity.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out all the required fields", Toast.LENGTH_SHORT).show();
                } else {
                    Stock newPantryItem = new Stock();
                    Food food = new Food();
                    food.setName(edtTxtIngredientName.getText().toString());
                    newPantryItem.setQuantity(Double.parseDouble(edtTxtQuantity.getText().toString()));
                    newPantryItem.setFood(food);
                    // this should be executed when all the values are valid and have been set as fields of newItem
                    // send the new item back to PantryActivity
                    try {
                        addItem = (AddItem) getActivity();
                        addItem.onAddItem(newPantryItem);
                        dismiss();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return builder.create();
    }

    // method to initialize UI components
    public void initViews(View view) {
        btnAddIngredient = view.findViewById(R.id.pantryAddIngredientButton);
        edtTxtIngredientName = view.findViewById(R.id.pantryAddIngredientName);
        edtTxtQuantity = view.findViewById(R.id.pantryAddIngredientQuantity);
        datePicker = view.findViewById(R.id.pantryAddIngredientDatePicker);
    }
}
