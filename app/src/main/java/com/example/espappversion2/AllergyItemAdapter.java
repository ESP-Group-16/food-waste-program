package com.example.espappversion2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllergyItemAdapter extends RecyclerView.Adapter<AllergyItemAdapter.ViewHolder> {

    private static final String TAG = "AllergyItemAdapter";
    private ArrayList<String> allergies = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();
    private Context context;

    public AllergyItemAdapter(Context context) {
        this.context = context;
        allergies = Utils.getInstance(context).getAllergies();
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

        holder.item.setChecked(allergies.contains(ingredients.get(holder.getAdapterPosition())));
        holder.item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println(new Gson().toJson(Utils.getInstance(context).getAllergies()));
                if(b) {
                    if(!allergies.contains(ingredients.get(holder.getAdapterPosition()))) {
                        Log.d(TAG, "onCheckedChanged: ");
                        Utils.getInstance(context).addAllergy(ingredients.get(holder.getAdapterPosition()));
                        //allergies.add(ingredients.get(holder.getAdapterPosition()));
                    }
                } else {
                    if(allergies.contains(ingredients.get(holder.getAdapterPosition()))) {
                        Log.d(TAG, "onCheckedChanged: ");
                        int index = allergies.indexOf(ingredients.get(holder.getAdapterPosition()));
                        Utils.getInstance(context).removeAllergy(index);
                        //allergies.remove(index);
                    }
                }
                System.out.println(new Gson().toJson(Utils.getInstance(context).getAllergies()));
            }
        });
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
