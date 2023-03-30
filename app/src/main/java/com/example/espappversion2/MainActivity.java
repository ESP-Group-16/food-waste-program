package com.example.espappversion2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements RegisterDialog.RegisterNewUser {

    private EditText edtTxtPassword, edtTxtEmail;
    private Button btnLogin, btnRegister;

    private FirebaseAuth mAuth;
    private String TAG = "MainActivity";

    @Override
    public void onRegister(String email, String password) {
        // TODO: finish registering new user
        createAccount(email, password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: login user
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {
                    signIn(email, password);
                }
            }
        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, PantryActivity.class);
                startActivity(intent);
                return false;
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

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            // open PantryActivity if user is signed in
            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
            intent.putExtra("email", currentUser.getEmail());
            startActivity(intent);
        }
    }

    // Firebase sign-in method
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // navigate user to PantryActivity
                            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    // Firebase create account method
    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(MainActivity.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Email: " + email + "\nPassword: " + password, Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // initialize UI components as fields
    private void initViews() {
        btnLogin = findViewById(R.id.activityMainLoginButton);
        btnRegister = findViewById(R.id.activityMainRegisterButton);
        edtTxtEmail = findViewById(R.id.activityMainEmailEdtTxt);
        edtTxtPassword = findViewById(R.id.activityMainPasswordEdtTxt);
    }
}