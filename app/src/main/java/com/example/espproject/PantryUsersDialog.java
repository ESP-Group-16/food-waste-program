package com.example.espproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



public class PantryUsersDialog extends DialogFragment {

    // TODO: declare UI components as fields

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_pantry_users, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        // TODO: get the list of users sent over from PantryFragment (using a Bundle)


        // TODO: set the textView to display pantry users


        return builder.create();
    }

    // TODO: finish (using the view.findViewById(R.layout.<insert components id>) method
    // method to initialize UI components
    private void initViews(View view) {

    }
}
