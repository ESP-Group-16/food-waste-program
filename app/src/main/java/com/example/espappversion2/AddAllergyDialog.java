package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddAllergyDialog extends DialogFragment {

    // Callback interface for sending passing new allergy item to profile activity.
    interface AddAllergy {
        void onAddAllergy(String allergy);
    }

    // TODO: Add Ui elements as fields...

    private AddAllergy addAllergy; // Instance of interface declared as a field


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_allergy, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        String allergy = ""; // Placeholder String.
        try {
            addAllergy = (AddAllergy) getActivity(); // Casting the calling activity to be AddAllergy interface object.
            addAllergy.onAddAllergy(allergy); // Calling method on calling activity (sending new object back)
        }catch(ClassCastException e){
            e.printStackTrace();
        }


        return builder.create();

    }

    private void initViews(View view) {
        // TODO: Initialise UI elements here...
    }

}
