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
import java.util.Objects;

public class AddPantryItemDialog extends DialogFragment {

    private Button btnAddIngredient;
    private EditText edtTxtIngredientName, edtTxtQuantity;
    private DatePicker datePicker;

    private Spinner unitSpinner;
    private RadioGroup rgStorageLocation;

    private AddPantryItemDialogListener listener;

    private String storageLocation;

    private ArrayList<String> units;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_pantry_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        units = getUnits();
        ArrayAdapter<String> unitsAdapter = new ArrayAdapter<>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, units);
        unitSpinner.setAdapter(unitsAdapter);
        unitSpinner.setSelection(0);

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get details of the food item from UI components and set them as the fields of newItem
                // make sure all the inputs are valid values with checks

                // if one field is empty
                if (edtTxtIngredientName.getText().toString().isEmpty() || edtTxtQuantity.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out all the required fields", Toast.LENGTH_SHORT).show();
                }
                else if (storageLocation == null) { // if storage location doesn't exist.
                    Toast.makeText(getActivity(), "Please select a storage location!", Toast.LENGTH_SHORT).show();
                } else {

                    Stock newPantryItem = new Stock();
                    Food food = new Food();

                    food.setName(edtTxtIngredientName.getText().toString());
                    food.setUnit(unitSpinner.getSelectedItem().toString());
                    newPantryItem.setQuantity(Double.parseDouble(edtTxtQuantity.getText().toString()));

                    newPantryItem.setFood(food);

                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();

                    newPantryItem.setExpiresAt(day+"/"+month+"/"+year);

                    listener.applyPantryItemChanges(storageLocation, newPantryItem);
                    dismiss();
                }
            }
        });

        rgStorageLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.dialogAddPantryItemFridgeRB:
                        storageLocation = "fridge";
                        //Toast.makeText(getActivity(), "Fridge selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.dialogAddPantryItemFreezerRB:
                        storageLocation = "freezer";
                        //Toast.makeText(getActivity(), "Freezer selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.dialogAddPantryItemCupboardRB:
                        storageLocation = "cupboard";
                        //Toast.makeText(getActivity(), "Cupboard selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                //Toast.makeText(getActivity(), storageLocation, Toast.LENGTH_SHORT).show();
            }
        });

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
        edtTxtQuantity = view.findViewById(R.id.pantryAddIngredientQuantity);
        datePicker = view.findViewById(R.id.pantryAddIngredientDatePicker);
        rgStorageLocation = view.findViewById(R.id.dialogAddPantryItemStorageLocationRG);
        unitSpinner = view.findViewById(R.id.pantryAddIngredientUnitSelector);
    }

    public interface AddPantryItemDialogListener{
        void applyPantryItemChanges(String storageloc, Stock stock);

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
