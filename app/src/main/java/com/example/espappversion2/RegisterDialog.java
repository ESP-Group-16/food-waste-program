package com.example.espappversion2;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RegisterDialog extends DialogFragment {

    public interface RegisterUser {
        void onRegister(User user);
    }

    // UI components
    private Button btnRegister;
    private EditText edtTxtEmail, edtTxtPassword, edtTxtRepeatPassword;

    private Repository repo;
    private RegisterUser registerUser;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_register, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);
        //repo = new Repository(getActivity());

        initViews(view);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString();
                String rePassword = edtTxtRepeatPassword.getText().toString();
                // check if all fields have data
                if(!email.isEmpty() && !password.isEmpty() && !rePassword.isEmpty()) {
                    // check if username exists
                    if(!Utils.getInstance(getActivity()).userExists(email)) {
                        // check if passwords match
                        if(password.equals(rePassword)) {
                            User user = new User(email, password);
                            // send new user back to MainActivity to be registered
                            try {
                                registerUser = (RegisterUser) getActivity();
                                registerUser.onRegister(user);
                                dismiss();
                            } catch (ClassCastException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
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