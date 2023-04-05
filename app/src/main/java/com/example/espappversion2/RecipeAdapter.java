package com.example.espappversion2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public interface NavigateToRecipeFragment {
        void onGoToRecipeFragment();
    }

    private NavigateToRecipeFragment navigateToRecipeFragment;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context context;

    private Repository repository;

    public RecipeAdapter(Context context) {
        this.context = context;
        this.repository = new Repository(context);
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

        holder.txtRecipeName.setText(currentRecipe.getName());
        holder.btnFavourite.setText(repository.containsRecipeInFavourites(currentRecipe)? "Unfavourite" : "Favourite");

        Glide.with(context)
                .asBitmap()
                .load(recipes.get(holder.getAdapterPosition()).getImageURL())
                .into(holder.recipeImage);

        holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: add recipe to favourites
                if(!repository.containsRecipeInFavourites(currentRecipe)) {
                    repository.addRecipeToFavourites(currentRecipe);
                    holder.btnFavourite.setText("Unfavourite");
                    Toast.makeText(context, currentRecipe.getName() + " added to favourite recipes", Toast.LENGTH_SHORT).show();
                } else {
                    repository.removeRecipeFromFavourites(currentRecipe);
                    holder.btnFavourite.setText("Favourite");
                    Toast.makeText(context, currentRecipe.getName() + " removed from favourite recipes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to RecipeFragment to display details about recipe
                try {
                    navigateToRecipeFragment = (NavigateToRecipeFragment) context;
                    navigateToRecipeFragment.onGoToRecipeFragment();
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
        private Button btnFavourite;
        private ImageView recipeImage;
        private TextView txtRecipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize UI components
            btnFavourite = itemView.findViewById(R.id.recipeItemFavouriteButton);
            txtRecipeName = itemView.findViewById(R.id.recipeItemRecipeNameTxt);
            parent = itemView.findViewById(R.id.recipeItemParent);
            recipeImage = itemView.findViewById(R.id.recipeItemImg);
        }
    }
}
