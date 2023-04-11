package com.example.espappversion2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder> {

    private ArrayList<Stock> items, fridgeItems, freezerItems, cupboardItems;
    private Context context;

    public SelectedItemsAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
        fridgeItems = new ArrayList<>();
        freezerItems = new ArrayList<>();
        cupboardItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock currentItem = items.get(holder.getAdapterPosition());
        holder.txtItemName.setText(currentItem.getFood().getName());
        if(currentItem.getQuantity() > 0) {
            holder.txtUnitNumber.setText(currentItem.getQuantity() + " " + currentItem.getFood().getUnit());
        } else {
            holder.txtUnitNumber.setText(currentItem.getFood().getUnit());
        }

        if(currentItem.getExpiresAt() != null) {
            holder.expiryDateEdtTxt.setText(currentItem.getExpiresAt());
        }

        // set expiry date when text changes
        holder.expiryDateEdtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentItem.setExpiresAt(holder.expiryDateEdtTxt.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.rgStorageLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.selectedItemFridgeRB:
                        fridgeItems.add(currentItem);
                        freezerItems.remove(currentItem);
                        cupboardItems.remove(currentItem);
                        break;
                    case R.id.selectedItemFreezerRB:
                        freezerItems.add(currentItem);
                        fridgeItems.remove(currentItem);
                        cupboardItems.remove(currentItem);
                        break;
                    case R.id.selectedItemCupboardRB:
                        cupboardItems.add(currentItem);
                        freezerItems.remove(currentItem);
                        fridgeItems.remove(currentItem);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Stock> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public ArrayList<Stock> getFridgeItems() {
        for(Stock s : items) {
            if(!fridgeItems.contains(s) && !freezerItems.contains(s) && !cupboardItems.contains(s)) {
                fridgeItems.add(s);
            }
        }
        return fridgeItems;
    }

    public ArrayList<Stock> getFreezerItems() {
        return freezerItems;
    }

    public ArrayList<Stock> getCupboardItems() {
        return cupboardItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName, txtUnitNumber;
        private EditText expiryDateEdtTxt;
        private RadioGroup rgStorageLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.selectedItemName);
            txtUnitNumber = itemView.findViewById(R.id.selectedItemUnitTxt);
            expiryDateEdtTxt = itemView.findViewById(R.id.selectedItemExpiryEdtTxt);
            rgStorageLocation = itemView.findViewById(R.id.selectedItemStorageLocationRG);
        }
    }
}
