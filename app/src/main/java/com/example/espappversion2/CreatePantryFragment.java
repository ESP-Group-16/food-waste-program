package com.example.espappversion2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CreatePantryFragment extends Fragment {

    private EditText edtTxtPantryName, edtTxtJoinCode;
    private Button btnCreatePantry, btnJoinPantry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_pantry, container, false);

        initViews(view);

        btnCreatePantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if pantry name is valid and create pantry TODO: check this with the database
                String name = edtTxtPantryName.getText().toString();
                if(!name.isEmpty()) {
                    goToPantry();
                }
            }
        });

        btnJoinPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if pantry name is valid and join pantry TODO: check this with the database
                String name = edtTxtJoinCode.getText().toString();
//                if(!name.isEmpty()) {
//                    goToPantry();
//                }
                goToPantry();
            }
        });

        return view;
    }

    private void goToPantry() {
        // open PantryFragment with the new pantry TODO: pass the pantry as an argument to the fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activityPantryFragmentContainer, new PantryFragment());
        transaction.commit();
    }

    private void initViews(View view) {
        edtTxtPantryName = view.findViewById(R.id.fragmentPantryNameEdtTxt);
        edtTxtJoinCode = view.findViewById(R.id.fragmentPantryCodeEdtTxt);
        btnCreatePantry = view.findViewById(R.id.fragmentPantryCreateButton);
        btnJoinPantry = view.findViewById(R.id.fragmentPantryJoinButton);
    }
}
