package com.example.espappversion2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Stock> items;

    public SelectedItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set UI components to reflect the data
        holder.checkBoxSelectedItem.setText(items.get(holder.getAdapterPosition()).getFood().getName());

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

        private CheckBox checkBoxSelectedItem;
        private EditText edtTxtExpiryDate;
        private CardView parent;
        private TextView txtUnitNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize UI components
            checkBoxSelectedItem = itemView.findViewById(R.id.selectedItemCheckBox);
            edtTxtExpiryDate = itemView.findViewById(R.id.selectedItemExpiryDateEdtTxt);
            parent = itemView.findViewById(R.id.selectedItemParent);
            txtUnitNumber = itemView.findViewById(R.id.selectedItemUnitText);
        }
    }
}
