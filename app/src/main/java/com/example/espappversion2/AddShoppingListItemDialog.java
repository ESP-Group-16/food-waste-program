package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class AddShoppingListItemDialog extends DialogFragment {

    public interface AddShoppingListItem {
        void onAddShoppingListItem(Stock item, String storageLocation);
    }

    private AddShoppingListItem addShoppingListItem;

    // UI components
    private Button btnAddItem;
    private EditText edtTxtItemName, edtTxtQuantity;
    private Spinner unitSpinner;

    private ArrayList<String> units;
    private String storageLocation;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_shopping_list_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        // set elements of unit spinner
        units = getUnits();
        ArrayAdapter<String> unitsAdapter = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, units);
        unitSpinner.setAdapter(unitsAdapter);
        unitSpinner.setSelection(0);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(), "Item selected: " + unitSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                storageLocation = null;
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get details of new item from UI components
                Stock newItem = new Stock();
                if(!edtTxtItemName.getText().toString().isEmpty() && !edtTxtQuantity.getText().toString().isEmpty()) {
                    Food food = new Food();
                    food.setName(edtTxtItemName.getText().toString());
                    food.setUnit(unitSpinner.getSelectedItem().toString());
                    newItem.setFood(food);
                    newItem.setQuantity(Double.parseDouble(edtTxtQuantity.getText().toString()));

                    // send the new item back to ShoppingListFragment
                    try {
                        addShoppingListItem = (AddShoppingListItem) getActivity();
                        addShoppingListItem.onAddShoppingListItem(newItem, storageLocation);
                    } catch(ClassCastException e) {
                        e.printStackTrace();
                    }

                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "Please fill out all the required fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return builder.create();
    }

    private void initViews(View view) {
        btnAddItem = view.findViewById(R.id.dialogAddShoppingListItemAddButton);
        edtTxtItemName = view.findViewById(R.id.dialogAddShoppingListItemNameEdtTxt);
        edtTxtQuantity = view.findViewById(R.id.dialogAddShoppingListItemQuantityEdtTxt);
        unitSpinner = view.findViewById(R.id.dialogAddShoppingListItemUnitSpinner);
    }

    private ArrayList<String> getUnits() {
        ArrayList<String> units = new ArrayList<>();
        units.add("Kg");
        units.add("L");
        units.add("Pc");
        units.add("Ml");
        units.add("Pint");
        units.add("g");
        return units;
    }
}
