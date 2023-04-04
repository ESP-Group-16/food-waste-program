package com.example.espappversion2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PantryItemsAdapter extends RecyclerView.Adapter<PantryItemsAdapter.ViewHolder> {

    private ArrayList<Stock> items = new ArrayList<>();
    private Context context;

    private String storageLocation;

    private Repository repository;

    private RangeSlider sliderQty;
    private TextView txtQty;

    public PantryItemsAdapter(Context context, String storageLocation) {
        this.context = context;
        this.storageLocation = storageLocation;
        this.repository = new Repository(context);
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
                if(items.get(holder.getAdapterPosition()).getQuantity() > 0.0) {
                    // display dialog to ask if user wants to delete or modify item
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setMessage("Are you sure you want to delete this item?");
                    if(items.get(holder.getAdapterPosition()).getFood() != null && items.get(holder.getAdapterPosition()).getFood().getName() != null) {
                        builder.setTitle("Delete " + items.get(holder.getAdapterPosition()).getFood().getName() + " ?");
                    } else {
                        builder.setTitle("Use item");
                    }

                    // Inflate and set the layout for the dialog
                    // Pass null as the parent view because its going in the dialog layout
                    builder.setView(LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_remove_pantry_item, null));

                    builder.setPositiveButton("Use", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Utils.getInstance(context).editPantryItemQuantity(storageLocation, holder.getAdapterPosition(), (double) sliderQty.getValues().get(0));
                                    //repository.useStockItem(storageLocation, items.get(holder.getAdapterPosition()), (double) sliderQty.getValues().get(0));
                                    updateItems();
                                    notifyDataSetChanged();
                                }
                            })
                            .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // don't do anything
                                }
                            })
                            .setNegativeButton("Remove All", new DialogInterface.OnClickListener() {
                                @Override
                                public  void onClick(DialogInterface dialogInterface, int i) {
                                    // delete the item form pantry
                                    //Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    //repository.removeStockItem(storageLocation, items.get(holder.getAdapterPosition()));
                                    Utils.getInstance(context).removePantryItem(storageLocation, holder.getAdapterPosition());
                                    updateItems();
                                    notifyDataSetChanged();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    sliderQty = (RangeSlider) alert.findViewById(R.id.pantryRemoveDialogSlider);
                    txtQty = (TextView) alert.findViewById(R.id.pantryRemoveDialogQtyTxt);
                    sliderQty.setValueTo((float)items.get(holder.getAdapterPosition()).getQuantity());

                    double qty = items.get(holder.getAdapterPosition()).getQuantity();

                    // if statement to determine step size of the counter
                    if (qty < 10) {
                        // set step size of 0.05
                        sliderQty.setStepSize(0.05f);
                    } else if (qty < 50) {
                        // set step size of 0.5
                        sliderQty.setStepSize(0.5f);
                    } else if (qty < 200) {
                        sliderQty.setValueTo(5.0f * (float)Math.ceil(items.get(holder.getAdapterPosition()).getQuantity() / 5)); // rounding UP to the nearest 5 to allow step size of 5
                        // set step size of 5
                        sliderQty.setStepSize(5.0f);
                    } else {
                        sliderQty.setValueTo(25.0f * (float)Math.ceil((float)items.get(holder.getAdapterPosition()).getQuantity() / 25)); // rounding UP to the nearest 25 to allow step size of 25
                        // set step size of 25
                        sliderQty.setStepSize(25.0f);
                    }
                    txtQty.setText(sliderQty.getValues().get(0).toString() + " " + items.get(holder.getAdapterPosition()).getFood().getUnit());

                    // change listener to update the text next to the slider whenever the slider changes
                    sliderQty.addOnChangeListener(new RangeSlider.OnChangeListener() {
                        @Override
                        public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                            List<Float> b = sliderQty.getValues();
                            double showVal = (double) Math.round(b.get(0) * 100) / 100; // doing rounding to avoid floating point errors
                            // without this, you get values like 17.99999999997 rather than 17.8
                            if (showVal > items.get(holder.getAdapterPosition()).getQuantity()) {
                                txtQty.setText(items.get(holder.getAdapterPosition()).getQuantity() + " " + items.get(holder.getAdapterPosition()).getFood().getUnit());
                            } else {
                                txtQty.setText(showVal + " " + items.get(holder.getAdapterPosition()).getFood().getUnit());
                            }
                        }
                    });
                } else {
                    Utils.getInstance(context).removePantryItem(storageLocation, holder.getAdapterPosition());
                    updateItems();
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void updateItems() {
        int storageLocationIndex = Arrays.asList(Pantry.STORAGE_LOCATIONS).indexOf(storageLocation);
        setItems(Utils.getInstance(context).getCurrentUser().getPantry().getPantryItems().get(Pantry.STORAGE_LOCATIONS[storageLocationIndex]));
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
