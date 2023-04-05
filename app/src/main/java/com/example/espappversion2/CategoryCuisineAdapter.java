package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryCuisineAdapter extends RecyclerView.Adapter<CategoryCuisineAdapter.ViewHolder> {

    public interface CategoryCuisineSelection {
        void onCategoryCuisineSelected(String categoryOrCuisine, String selection);
    }

    private CategoryCuisineSelection categoryCuisineSelection;

    private ArrayList<String> categoriesCuisinesList = new ArrayList<>();
    private String categoryOrCuisine;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCategoryCuisineName.setText(categoriesCuisinesList.get(holder.getAdapterPosition()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send selection back to RecipeActivity to open RecipeListFragment with correct category/cuisine
                try {
                    categoryCuisineSelection = (CategoryCuisineSelection) context;
                    categoryCuisineSelection.onCategoryCuisineSelected(categoryOrCuisine, categoriesCuisinesList.get(holder.getAdapterPosition()));
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesCuisinesList.size();
    }

    public CategoryCuisineAdapter(String categoryOrCuisine, Context context) {
        this.categoryOrCuisine = categoryOrCuisine;
        this.context = context;
    }


    public void setList(ArrayList<String> list) {
        this.categoriesCuisinesList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategoryCuisineName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.categoryCuisineItemParent);
            txtCategoryCuisineName = itemView.findViewById(R.id.categoryCuisineItemNameTxt);
        }
    }
}
