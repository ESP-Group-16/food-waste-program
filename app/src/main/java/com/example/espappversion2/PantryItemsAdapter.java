package com.example.espappversion2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryItemsAdapter extends RecyclerView.Adapter<PantryItemsAdapter.ViewHolder> {

    private ArrayList<Stock> items = new ArrayList<>();
    private Context context;

    private String storageLocation;

    private Repository repository;

    public PantryItemsAdapter(Context context, String storageLocation) {
        this.context = context;
        this.storageLocation = storageLocation;
        this.repository = new Repository();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_item, parent, false);
        return new ViewHolder(view);
    }

    // called when recycler view items need to be updated (when they are scrolled)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(items.get(holder.getAdapterPosition()).getFood() != null && items.get(holder.getAdapterPosition()).getFood().getName() != null) {
            holder.txtItemName.setText(items.get(holder.getAdapterPosition()).getFood().getName());
        }
        holder.txtQuantity.setText(items.get(holder.getAdapterPosition()).getQuantity() + " " + items.get(holder.getAdapterPosition()).getFood().getUnit());

        holder.txtExpiryDate.setText(String.valueOf(items.get(holder.getAdapterPosition()).getExpiresAt()));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display dialog to ask if user wants to delete or modify item
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete this item?");
                if(items.get(holder.getAdapterPosition()).getFood() != null && items.get(holder.getAdapterPosition()).getFood().getName() != null) {
                    builder.setTitle("Delete " + items.get(holder.getAdapterPosition()).getFood().getName() + " ?");
                } else {
                    builder.setTitle("Delete");
                }
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // delete the item form pantry
                                //Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                repository.removeStockItem(storageLocation, items.get(holder.getAdapterPosition()));
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // don't do anything
                            }
                        });
                builder.create().show();
            }
        });
    }

    public void setItems(ArrayList<Stock> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // number of items in the recycler view
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView txtItemName, txtExpiryDate, txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize UI components
            parent = itemView.findViewById(R.id.pantryItemParent);
            txtItemName = itemView.findViewById(R.id.txtPantryItemName);
            txtExpiryDate = itemView.findViewById(R.id.txtPantryItemExpiryDate);
            txtQuantity = itemView.findViewById(R.id.txtPantryItemQuantity);
        }
    }
}
