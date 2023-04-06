package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements VolleyCallback {

    public interface NavigateToRecipeFragment {
        void onGoToRecipeFragment(String selectedRecipe, String backList, String extra);
    }

    public interface ReloadList {
        void onReloadList();
    }

    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {

    }

    @Override
    public void onFailure(VolleyError error) {

    }

    private NavigateToRecipeFragment navigateToRecipeFragment;
    private ReloadList reloadList;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context context;
    private String recipeListMode, extra;
    private RecipeAPI recipeAPI;
    private TextView txtHolderCarbon;

    public RecipeAdapter(Context context, String recipeListMode, String extra) {
        this.context = context;
        this.recipeListMode = recipeListMode;
        this.extra = extra;
        recipeAPI = new RecipeAPI(context);
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
        Recipe currentRecipe = recipes.get(holder.getAdapterPosition());

        try {
            recipeAPI.getRecipeCarbon(this, currentRecipe);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        holder.txtRecipeName.setText(currentRecipe.getName());
        holder.favouriteImg.setVisibility(Utils.getInstance(context).isRecipeInFavourites(currentRecipe.getName())? View.VISIBLE : View.GONE);
        holder.notFavouriteImg.setVisibility(!Utils.getInstance(context).isRecipeInFavourites(currentRecipe.getName())? View.VISIBLE : View.GONE);

        Glide.with(context)
                .asBitmap()
                .load(recipes.get(holder.getAdapterPosition()).getImageURL())
                .into(holder.recipeImage);

        holder.favouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getInstance(context).removeRecipeFromFavourites(recipes.get(holder.getAdapterPosition()).getName());
                holder.favouriteImg.setVisibility(View.GONE);
                holder.notFavouriteImg.setVisibility(View.VISIBLE);
                if(recipeListMode.equals("favourites")) {
                    recipes.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }
            }
        });

        holder.notFavouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getInstance(context).addRecipeToFavourites(recipes.get(holder.getAdapterPosition()).getName());
                holder.favouriteImg.setVisibility(View.VISIBLE);
                holder.notFavouriteImg.setVisibility(View.GONE);
            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to RecipeFragment to display details about recipe
                try {
                    navigateToRecipeFragment = (NavigateToRecipeFragment) context;
                    navigateToRecipeFragment.onGoToRecipeFragment(recipes.get(holder.getAdapterPosition()).getName(), recipeListMode, extra);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
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

        private CardView parent;
        private ImageView recipeImage, favouriteImg, notFavouriteImg;
        private TextView txtRecipeName, txtCarbon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize UI components
            txtRecipeName = itemView.findViewById(R.id.recipeItemRecipeNameTxt);
            txtCarbon = itemView.findViewById(R.id.recipeItemCarbonEmission);
            parent = itemView.findViewById(R.id.recipeItemParent);
            recipeImage = itemView.findViewById(R.id.recipeItemImg);
            favouriteImg = itemView.findViewById(R.id.recipeItemFavourite);
            notFavouriteImg = itemView.findViewById(R.id.recipeItemNotFavourite);
        }
    }
}
