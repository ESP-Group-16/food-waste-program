package com.example.espappversion2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edtTxtPassword, edtTxtEmail;
    private Button btnLogin, btnRegister;

    private Repository repo=new Repository();

    private String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: login user
                String email = edtTxtEmail.getText().toString().trim();
                String password = edtTxtPassword.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    //check if email and password match
                    User user=repo.GetUserFromName(email);
                    try{
                        if (password.equals(user.getPassword())){
                            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
                            startActivity(intent);
                            //set currentuserid as user get user iD
                            repo.SetCurrentUserID(user.getUserId());
                        }
                    }
                    catch(Exception e){
                        Log.d(TAG, "invalid login");
                    }
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeAPI huh = new RecipeAPI(getApplicationContext());
                huh.getRecipesByCuisine(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Handle API response
                        // This will now have a JSON object if API call is successful
                        // we can make this into a recipe object
                        System.out.println(response.toString());
                    }

                    @Override
                    public void onFailure(VolleyError error) {
                        // Handle error response
                        error.printStackTrace();
                    }
                }, "Italian");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null) {
//            // open PantryActivity if user is signed in
//            Intent intent = new Intent(MainActivity.this, PantryActivity.class);
//            intent.putExtra("email", currentUser.getEmail());
//            startActivity(intent);
//        }
    }

    // initialize UI components as fields
    private void initViews() {
        btnLogin = findViewById(R.id.activityMainLoginButton);
        btnRegister = findViewById(R.id.activityMainRegisterButton);
        edtTxtEmail = findViewById(R.id.activityMainEmailEdtTxt);
        edtTxtPassword = findViewById(R.id.activityMainPasswordEdtTxt);
    }
}