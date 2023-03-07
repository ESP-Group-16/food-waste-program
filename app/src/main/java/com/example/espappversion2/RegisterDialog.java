package com.example.espappversion2;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RegisterDialog extends DialogFragment {

    // UI components
    private Button btnRegister;
    private EditText edtTxtEmail, edtTxtPassword, edtTxtRepeatPassword;

    private Repository repo=new Repository();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_register, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);

        initViews(view);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    // TODO: check if data meets registration criteria - add more checks here
                    // note that the password must be at least 6 characters long
                    // check if username is taken
                    User user=new User(email,password);
                    repo.StoreUser(user);
                }
            }
        });

        return builder.create();
    }

    private void initViews(View view) {
        btnRegister = view.findViewById(R.id.dialogRegisterButton);
        edtTxtEmail = view.findViewById(R.id.dialogRegisterEmailEdtTxt);
        edtTxtPassword = view.findViewById(R.id.dialogRegisterPasswordEdtTxt);
        edtTxtRepeatPassword = view.findViewById(R.id.dialogRegisterRepeatPasswordEdtTxt);
    }
}