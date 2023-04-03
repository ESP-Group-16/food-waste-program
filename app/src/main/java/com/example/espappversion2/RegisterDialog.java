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

    // UI components
    private Button btnRegister;
    private EditText edtTxtEmail, edtTxtPassword, edtTxtRepeatPassword;

    private Repository repo;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // display dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_register, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);
        repo = new Repository(getActivity());

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
                    if (repo.GetUserFromName(email)==null){
                        User user=new User(email,password);
                        repo.StoreUser(user);
                        Log.d(TAG, "Account created");
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Account could not be created. Check details are valid", Toast.LENGTH_SHORT).show();
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