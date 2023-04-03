package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class AddAllergyDialog extends DialogFragment {

    private EditText editTextAllergy; // New Allergy Text Box

    private TextView listOfAllergies; // Allergy List
    private AddAllergyDialogListener listener; // Listener to send dialog info to activity.

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Display Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set View = Custom View.
        LayoutInflater inflater = requireActivity().getLayoutInflater(); // use requireActivity instead of getActivity to avoid NullPointerException.
        View view = inflater.inflate(R.layout.dialog_add_allergy, null);

        builder.setView(view)
                .setTitle("Add or Remove Input Allergy")
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Cancel operations, therefore do nothing.
                    }
                })
                .setNegativeButton("Remove Allergy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String allergy = editTextAllergy.getText().toString();
                        boolean adremBool = false;
                        listener.applyAllergyChanges(allergy, adremBool);
                    }
                })
                .setPositiveButton("Add Allergy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String allergy = editTextAllergy.getText().toString();
                        boolean adremBool = true;
                        listener.applyAllergyChanges(allergy, adremBool);
                    }
                });

        // Initialise editText
        editTextAllergy = view.findViewById(R.id.editAllergyUI);
        listOfAllergies = view.findViewById(R.id.currentAllergyList);

        // Get information for textview
        Repository repo = new Repository(getActivity());
        StringBuilder caString = new StringBuilder();
        for (String i : repo.getAllergies()){
            caString.append(i).append("\n");
        }

        listOfAllergies.setText(caString.toString());

        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) { // context is our activity
        super.onAttach(context);

        try {
            listener = (AddAllergyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AddAllergyDialogListener");
        }
    }

    public interface AddAllergyDialogListener{
        void applyAllergyChanges(String allergy, boolean adremBool); // allergy = text entered; adremBool = add or remove such text from allergies list.

    }

}
