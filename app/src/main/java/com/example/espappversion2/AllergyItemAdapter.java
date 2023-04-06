package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllergyItemAdapter extends RecyclerView.Adapter<AllergyItemAdapter.ViewHolder> {

    private ArrayList<String> ingredients = new ArrayList<>();
    private Context context;

    public AllergyItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allergy_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(ingredients.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setList(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox item;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.allergyItemNameCheckBox);
            parent = itemView.findViewById(R.id.allergyItemParent);
        }
    }
}
