package com.example.espproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

//import com.example.espproject.databinding.FragmentCreatePantryBinding;

public class CreatePantryFragment extends Fragment {

//    private FragmentCreatePantryBinding binding;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        binding = FragmentCreatePantryBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    }
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        binding.buttonSubmitcreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CreatePantryFragment.this)
//                        .navigate(R.id.action_createPantryFragment_to_pantryFragment);
//            }
//        });
//        binding.buttonSubmitjoin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(CreatePantryFragment.this)
//                        .navigate(R.id.action_createPantryFragment_to_pantryFragment);
//            }
//        });
//    }

//    // callback interface for passing data back to PantryActivity
//    public interface CreateNewPantry {
//        void onCreateNewPantry(String name);
//    }
//
//    private CreatePantryDialog.CreateNewPantry createNewPantry;
//
//    // TODO: declare UI components as fields
//    private Button btnCreate;
//
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        // display dialog
//        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_create_pantry, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view);
//
//        initViews(view);
//
//        btnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // TODO: get the name of the new pantry, make sure it's valid and unique
//                // just make sure the name is valid for now as we need Firebase for checking if it's unique
//                String name = null; // change
//
//
//                // this should be executed when all the values are valid and have been set as fields of newItem
//                // send the name of the new pantry back to PantryActivity
//                try {
//                    createNewPantry = (CreatePantryDialog.CreateNewPantry) getActivity();
//                    createNewPantry.onCreateNewPantry(name);
//                } catch(ClassCastException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        return builder.create();
//    }
//
//
//    // TODO: finish (using the view.findViewById(R.layout.<insert components id>) method
//    // method to initialize UI components
//    public void initViews(View view) {
//        //btnCreate = view.findViewById(R.id.btnCreatePantry);
//    }
}
