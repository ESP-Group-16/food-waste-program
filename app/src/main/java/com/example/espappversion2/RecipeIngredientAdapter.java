package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;
    private Context context;

    public RecipeIngredientAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient item = ingredients.get(holder.getAdapterPosition());
        if(item.getQuantity() > 0) {
            // This is to show symbol . instead of ,
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.UK);
            // Define the maximum number of decimals (number of symbols #)
            DecimalFormat df = new DecimalFormat("#.##########", otherSymbols);
            holder.txtName.setText(df.format(item.getQuantity()) + item.getFood().getUnit() + " " + item.getFood().getName());
        } else {
            holder.txtName.setText(item.getFood().getUnit() + " " + item.getFood().getName());
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtIngredientName);
            parent = itemView.findViewById(R.id.recipeIngredientParent);
        }
    }
}
