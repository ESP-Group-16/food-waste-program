package com.example.espproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PantryFragment extends Fragment {

    // TODO: declare UI components as fields

    private Pantry pantry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        // TODO: initialize UI components
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
