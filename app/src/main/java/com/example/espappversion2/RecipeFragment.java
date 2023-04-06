package com.example.espappversion2;

import static com.example.espappversion2.RecipeMenuFragment.RECIPE_MODE;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeFragment extends Fragment implements VolleyCallback {

    @Override
    public void onSuccess(JSONObject response, String resultFor) throws JSONException {
        if(resultFor.equals("recipe_by_exact_name")) {
            recipe = new Recipe(response);

            try {
                recipeAPI.getRecipeCarbon(this, recipe);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            // set UI components to display details of selected recipe
            txtRecipeName.setText(recipe.getName());
            Glide.with(getActivity())
                    .asBitmap()
                    .load(recipe.getImageURL())
                    .into(imgRecipe);

            System.out.println("Recipe ingredients: " + recipe.getIngredients());
            System.out.println("Recipe instructions: " + recipe.getSteps());
            System.out.println(recipe.getIngredients().getClass().getName());
            System.out.println(recipe.getIngredients().get(0).getClass().getName());
            System.out.println(recipe.getSteps().getClass().getName());

            txtInstructions.setText(recipe.getSteps());

            // set ingredients to be displayed in recycler view
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for(Ingredient ingredient : recipe.getIngredients()) {
                if(!ingredient.getFood().getName().isEmpty() && ingredient.getFood().getUnit() != null && ingredient.getFood().getName() != null) {
                    ingredients.add(ingredient);
                }
            }
            adapter.setItems(ingredients);
            recViewIngredients.setAdapter(adapter);
            recViewIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));

            btnAddIngredientsToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                            .setTitle("Add items to shopping list")
                            .setMessage("Do you want to add all the ingredients to the shopping list?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // TODO: add all ingredients to shopping list
                                    for(Ingredient ingredient : ingredients) {
                                        //Utils.getInstance(getActivity()).addShoppingListItem(ingredient.makeStock());
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // do nothing
                                }
                            });
                }
            });

            // see if recipe is in favourites and set visibility of buttons accordingly
            if(Utils.getInstance(getActivity()).isRecipeInFavourites(recipe.getName())) {
                btnUnFavouriteRecipe.setVisibility(View.GONE);
                btnFavouriteRecipe.setVisibility(View.VISIBLE);
            } else {
                btnFavouriteRecipe.setVisibility(View.GONE);
                btnUnFavouriteRecipe.setVisibility(View.VISIBLE);
            }
            btnFavouriteRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // remove recipe from favourites and change visibility
                    btnFavouriteRecipe.setVisibility(View.GONE);
                    btnUnFavouriteRecipe.setVisibility(View.VISIBLE);
                    Utils.getInstance(getActivity()).removeRecipeFromFavourites(recipe.getName());
                }
            });

            btnUnFavouriteRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // add recipe to favourites and change visibility
                    btnUnFavouriteRecipe.setVisibility(View.GONE);
                    btnFavouriteRecipe.setVisibility(View.VISIBLE);
                    Utils.getInstance(getActivity()).addRecipeToFavourites(recipe.getName());
                }
            });
        } else if(resultFor.equals("recipe_carbon")) {
            if(displayCarbon) {
                String carbonEmission = response.getString("carbon");
                txtCarbonEmission.setText("Carbon emission: " + carbonEmission);
                txtCarbonEmission.setTextColor(getResources().getColor(R.color.text_color));
                if(carbonEmission.equals("Very Low")) {
                    txtCarbonEmission.setTextColor(getResources().getColor(R.color.very_high));
                } else if(carbonEmission.equals("Low")){
                    txtCarbonEmission.setTextColor(getResources().getColor(R.color.low));
                } else if(carbonEmission.equals("Medium")) {
                    txtCarbonEmission.setTextColor(getResources().getColor(R.color.medium));
                } else if(carbonEmission.equals("High")) {
                        txtCarbonEmission.setTextColor(getResources().getColor(R.color.high));
                } else if((carbonEmission.equals("Very High"))) {
                    txtCarbonEmission.setTextColor(getResources().getColor(R.color.very_high));
                }
            } else {
                txtCarbonEmission.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onFailure(VolleyError error) {

    }

    public static final boolean displayCarbon = true;

    private Button btnBack, btnFavouriteRecipe, btnUnFavouriteRecipe, btnAddIngredientsToCart;
    private TextView txtRecipeName, txtInstructions, txtCarbonEmission;
    private ImageView imgRecipe;
    private RecyclerView recViewIngredients;

    private String backList, extra;
    private String recipeName;
    private Recipe recipe;
    private RecipeAPI recipeAPI;
    private RecipeIngredientAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        initViews(view);
        recipeAPI = new RecipeAPI(getActivity());
        adapter = new RecipeIngredientAdapter(getActivity());
        Bundle bundle = getArguments();
        if(bundle != null) {
            backList = bundle.getString("recipe_list", null);
            extra = bundle.getString("extra", null);
            recipeName = bundle.getString("recipe", null);
            if(recipeName != null) {
                recipeAPI.getRecipeByExactName(this, recipeName);

            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user back to RecipeListFragment
                // TODO: go back to the list of search results that was displayed previously
                RecipeListFragment fragment = new RecipeListFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString(RECIPE_MODE, backList);
                if(extra != null) {
                    if(backList.equals("search")) {
                        bundle1.putString("search", extra);
                    } else if(backList.equals("search_by_cuisine")) {
                        bundle1.putString("cuisine", extra);
                    } else if(backList.equals("search_by_category")) {
                        bundle1.putString("category", extra);
                    }
                }
                fragment.setArguments(bundle1);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activityRecipeFragmentContainer, fragment);
                transaction.commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        btnBack = view.findViewById(R.id.fragmentRecipeBackBtn);
        btnFavouriteRecipe = view.findViewById(R.id.fragmentRecipeFavouriteBtn);
        btnUnFavouriteRecipe = view.findViewById(R.id.fragmentRecipeNotFavouriteBtn);
        btnAddIngredientsToCart = view.findViewById(R.id.fragmentRecipeAddToShopBtn);
        txtRecipeName = view.findViewById(R.id.fragmentRecipeNameTxt);
        txtInstructions = view.findViewById(R.id.fragmentRecipeInstructionsTxt);
        txtCarbonEmission = view.findViewById(R.id.fragmentRecipeCarbonEmission);
        imgRecipe = view.findViewById(R.id.fragmentRecipeImg);
        recViewIngredients = view.findViewById(R.id.fragmentRecipeIngredientsRecycler);
    }
}
