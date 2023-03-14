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

import java.util.Objects;

public class AddPantryItemDialog extends DialogFragment {

    private Button btnAddIngredient;
    private EditText edtTxtIngredientName, edtTxtStorageLocation, edtTxtQuantity;
    private DatePicker datePicker;

    private AddPantryItemDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_pantry_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: get details of the food item from UI components and set them as the fields of newItem
                // TODO: make sure all the inputs are valid values with checks

                // if one field is empty
                if (edtTxtIngredientName.getText().toString().isEmpty() || edtTxtQuantity.getText().toString().isEmpty() || edtTxtStorageLocation.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out all the required fields", Toast.LENGTH_SHORT).show();
                }
                else if (!edtTxtStorageLocation.getText().toString().equalsIgnoreCase("cupboard")
                        && !edtTxtStorageLocation.getText().toString().equalsIgnoreCase("fridge")
                        && !edtTxtStorageLocation.getText().toString().equalsIgnoreCase("freezer")) { // if storage location doesn't exist.
                    Toast.makeText(getActivity(), "Storage Location must be one of the following:\n- cupboard\n- fridge\n- freezer", Toast.LENGTH_SHORT).show();
                } else {

                    Stock newPantryItem = new Stock();
                    Food food = new Food();

                    food.setName(edtTxtIngredientName.getText().toString());
                    newPantryItem.setQuantity(Double.parseDouble(edtTxtQuantity.getText().toString()));

                    String storageloc = edtTxtStorageLocation.getText().toString();

                    newPantryItem.setFood(food);

                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();

                    newPantryItem.setExpiresAt(day+"/"+month+"/"+year);

                    listener.applyPantryItemChanges(storageloc, newPantryItem);
                }
            }
        });

        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) { // context is our activity
        super.onAttach(context);

        try {
            listener = (AddPantryItemDialog.AddPantryItemDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AddPantryItemDialogListener");
        }
    }


    // method to initialize UI components
    public void initViews(View view) {
        btnAddIngredient = view.findViewById(R.id.pantryAddIngredientButton);
        edtTxtIngredientName = view.findViewById(R.id.pantryAddIngredientName);
        edtTxtStorageLocation = view.findViewById(R.id.pantryIngredientStorageLocation);
        edtTxtQuantity = view.findViewById(R.id.pantryAddIngredientQuantity);
        datePicker = view.findViewById(R.id.pantryAddIngredientDatePicker);
    }

    public interface AddPantryItemDialogListener{
        void applyPantryItemChanges(String storageloc, Stock stock);

    }

}
