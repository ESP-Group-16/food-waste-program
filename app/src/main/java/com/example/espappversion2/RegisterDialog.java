package com.example.espappversion2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RegisterDialog extends DialogFragment {

    public interface RegisterNewUser {
        void onRegister(String email, String password);
    }

    private RegisterNewUser registerNewUser;

    // UI components
    private Button btnRegister;
    private EditText edtTxtEmail, edtTxtPassword, edtTxtRepeatPassword;

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

                // TODO: check if data meets registration criteria - add more checks here
                // note that the password must be at least 6 characters long
                if(!email.isEmpty() && !password.isEmpty()) {
                    // send data back to MainActivity
                    try {
                        registerNewUser = (RegisterNewUser) getActivity();
                        registerNewUser.onRegister(email, password);
                        dismiss();
                    } catch(ClassCastException e) {
                        e.printStackTrace();
                    }
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