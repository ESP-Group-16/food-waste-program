package com.example.espappversion2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private Context context;
    private Repository repository;
    private ArrayList<Stock> items = new ArrayList<>();
    private ArrayList<Stock> selectedItems = new ArrayList<>();
    private int storageLocation;

    public ShoppingListAdapter(Context context, int storageLocation) {
        this.context = context;
        this.storageLocation = storageLocation;
        repository = new Repository(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set text of UI components
        if(items.get(holder.getAdapterPosition()).getFood() != null) {
            holder.txtItemName.setText(items.get(holder.getAdapterPosition()).getFood().getName());
        }
        if(items.get(holder.getAdapterPosition()).getQuantity() > 0) {
            // This is to show symbol . instead of ,
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.UK);
            // Define the maximum number of decimals (number of symbols #)
            DecimalFormat df = new DecimalFormat("#.##########", otherSymbols);
            holder.txtUnitNumber.setText(df.format(items.get(holder.getAdapterPosition()).getQuantity()) + " " + items.get(holder.getAdapterPosition()).getFood().getUnit());
        } else {
            holder.txtUnitNumber.setText(items.get(holder.getAdapterPosition()).getFood().getUnit());
        }

        holder.txtItemName.setChecked(selectedItems.contains(items.get(holder.getAdapterPosition())));

        holder.txtItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Stock selectedItem = items.get(holder.getAdapterPosition());
                if(b) {
                    //Toast.makeText(context, selectedItem.getFood().getName() + " selected", Toast.LENGTH_SHORT).show();
                    if(!selectedItems.contains(selectedItem)) {
                        selectedItems.add(selectedItem);
                    }
                } else {
                    //Toast.makeText(context, selectedItem.getFood().getName() + " unselected", Toast.LENGTH_SHORT).show();
                    if (selectedItems.remove(selectedItem)) {
                        //Toast.makeText(context, selectedItem.getFood().getName() + " removed from list", Toast.LENGTH_SHORT).show();
                    }
                }
                System.out.println(new Gson().toJson(selectedItem));
            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display dialog to ask if user wants to delete or modify item
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete this item?");
                if(items.get(holder.getAdapterPosition()).getFood() != null && items.get(holder.getAdapterPosition()).getFood().getName() != null) {
                    builder.setTitle("Delete " + items.get(holder.getAdapterPosition()).getFood().getName() + "?");
                } else {
                    builder.setTitle("Delete");
                }
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // delete the item form pantry
                                //Toast.makeText(context, "Deleted from shopping list", Toast.LENGTH_SHORT).show();
                                //repository.removeItemFromShoppingList(items.get(holder.getAdapterPosition()), storageLocation);
                                Utils.getInstance(context).removeShoppingListItem(holder.getAdapterPosition());
                                updateItems();
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

    private void updateItems() {
        setItems(Utils.getInstance(context).getCurrentUser().getShoppingList());
    }

    public ArrayList<Stock> getSelectedItems() {
        return selectedItems;
    }

    public void setItems(ArrayList<Stock> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUnitNumber;
        private CheckBox txtItemName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUnitNumber = itemView.findViewById(R.id.shoppingListItemUnitNumberTxt);
            txtItemName = itemView.findViewById(R.id.shoppingListItemCheckbox);
            parent = itemView.findViewById(R.id.shoppingListItemParent);
        }
    }
}
