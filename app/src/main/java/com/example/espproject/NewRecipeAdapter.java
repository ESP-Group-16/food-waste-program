package com.example.espproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewRecipeAdapter extends RecyclerView.Adapter<NewRecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recipe_item, parent, false);
        return new ViewHolder(view);
    }

    // called when recycler view items need to be updated (when they are scrolled)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: bind UI components of element to data
    }

    // number of items in the recycler view
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // TODO: add UI components as fields

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // TODO: initialize UI components
        }
    }
}
