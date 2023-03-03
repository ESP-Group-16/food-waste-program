package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context context;

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    // called when recycler view items need to be updated (when they are scrolled)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtRecipeName.setText(recipes.get(position).getName());

        holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: add recipe to favourites
            }
        });

        holder.btnAddIngredientsToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: add the ingredients of the recipe to cart
            }
        });
    }

    public void setItems(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    // number of items in the recycler view
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button btnAddIngredientsToCart, btnFavourite;
        private TextView txtRecipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize UI components
            btnFavourite = itemView.findViewById(R.id.recipeItemFavouriteButton);
            btnAddIngredientsToCart = itemView.findViewById(R.id.recipeItemAddIngredientsToCartButton);
            txtRecipeName = itemView.findViewById(R.id.recipeItemRecipeNameTxt);
        }
    }
}
