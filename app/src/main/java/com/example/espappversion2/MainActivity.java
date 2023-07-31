package com.example.espappversion2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RegisterDialog.RegisterUser {

    @Override
    public void onRegister(User user) {
        Utils.getInstance(this).registerUser(user);
        Toast.makeText(this, user.getUserName() + " registered", Toast.LENGTH_SHORT).show();
    }

    private EditText edtTxtPassword, edtTxtEmail;
    private Button btnLogin, btnRegister;
    private CheckBox checkBoxStayLoggedIn;

    private String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        initViews();
        //Utils.getInstance(this).clearUsers();
        //Utils.getInstance(this).clearCurrentUser();
        login(Utils.getInstance(this).getCurrentUserFromMemory());

//        if(Utils.getInstance(this).getCurrentUser() != null) {
//            if(Utils.getInstance(this).userExists(Utils.getInstance(this).getCurrentUser().getUserName())) {
//                login(Utils.getInstance(this).getCurrentUserFromMemory());
//            }
//        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString();

                // check if all fields are filled in
                if(!email.isEmpty() && !password.isEmpty()) {
                    // check if user exists
                    if(Utils.getInstance(MainActivity.this).userExists(email)) {
                        // check if password is valid
                        if(password.equals(Utils.getInstance(MainActivity.this).getUser(email).getPassword())) {
                            User user = Utils.getInstance(MainActivity.this).getUser(email);
                            login(user);
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User doesn't exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in the required fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display registration dialog
                RegisterDialog dialog = new RegisterDialog();
                dialog.show(getSupportFragmentManager(), "register user");
            }
        });

        btnRegister.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("All users:\n" + Utils.getInstance(MainActivity.this).getUsers());
                //System.out.println(Utils.getInstance(MainActivity.this).getUsers().getClass().getName());
                System.out.println("Current user: " + Utils.getInstance(MainActivity.this).getCurrentUser());
                return false;
            }
        });
    }


    private void login(User user) {
        Log.d(TAG, "login: called");
        if(user != null) {
            Utils.getInstance(this).setCurrentUser(user, checkBoxStayLoggedIn.isChecked());
            Toast.makeText(this, "Logged in as " + user.getUserName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    // initialize UI components as fields
    private void initViews() {
        btnLogin = findViewById(R.id.activityMainLoginButton);
        btnRegister = findViewById(R.id.activityMainRegisterButton);
        edtTxtEmail = findViewById(R.id.activityMainEmailEdtTxt);
        edtTxtPassword = findViewById(R.id.activityMainPasswordEdtTxt);
        checkBoxStayLoggedIn = findViewById(R.id.activityMainStayLoggedInCheckBox);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}