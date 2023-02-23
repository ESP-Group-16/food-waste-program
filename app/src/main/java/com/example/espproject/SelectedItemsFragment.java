package com.example.espproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.espproject.databinding.FragmentSelectedItemsBinding;

public class SelectedItemsFragment extends Fragment {

    private FragmentSelectedItemsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectedItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
