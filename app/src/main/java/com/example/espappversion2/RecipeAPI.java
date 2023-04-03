package com.example.espappversion2;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

// Ok so this API actually goes hard
// Have a look at the documentation https://www.themealdb.com/api.php
// also you have my explanation and visualisation below
// let's hope it works :)

/*

    if the name of the function is not plural, this applies:
    the response structure of the API is a json of the format:
    {
    "meals": [{

               "idMeal": int,
               "strMeal": String,           Title of recipe
               "strDrinkAlternate": ?
               "strCategory": String,       i.e. Vegetarian
               "strArea": String,           i.e. Italian
               "strInstructions": String,
               "strMealThumb": String,      This is the URL to a thumbnail of the recipe
               "strTags": String,           Separated by commas, i.e. "Pasta,Curry" with no space
               "strYoutube": String         This is the URL to a youtube vid of the recipe
               "strIngredient1": String     Ingredient1
               ...                                  ALL recipes will have 20 strIngredient items. usually the last ones will be null since
               "strIngredient20": String/null,      not many recipes have that many ingredients

               "strMeasure1": String/null,          The measure for each ingredient. Same logic applied as above
                ...
               "strMeasure20": String/null,
               OTHER STUFF MAY BE RETURNED BUT WE DON'T NEED IT

            }]
       }

     NOTICE THAT THE FORMAT IS "meals": ARRAY<JSONObject> so you will need to call the index 0 of meals to get the recipe



     if the name of a function is plural, the following applies:
     response is a JSONObject of the format:

    {
        "meals": [{
                   "strMealThumb": String,
                   "idMeal": int,
                   "strMeal": String,           Title of recipe
                    },
                    /// other recipe ///
                    }, ... {other recipe}]
     }

    NOTICE THIS RETURNS AN ARRAY OF RECIPES WITH THE "meals": ARRAY<JSONObject>


    ********************* IF YOU WANT A LIST OF *********************

    CATEGORIES:     www.themealdb.com/api/json/v1/1/list.php?c=list
    CUISINES:       www.themealdb.com/api/json/v1/1/list.php?a=list
    INGREDIENTS:    www.themealdb.com/api/json/v1/1/list.php?i=list

    *****************************************************************

 */
public class RecipeAPI {

    private Context mContext;
    public RecipeAPI(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Function which takes in a ArrayList<String> of ingredients.
     * Please make sure if an ingredient has a whitespace, replace it with "_"
     * @param callback used to return result to the calling activity
     * @param ingredients ArrayList<String> of ingredients to filter in recipes
     */
    public void getRecipeByMultipleIngredients(final VolleyCallback callback, ArrayList<String> ingredients) {
        String commaSeparatedList = ingredients.toString();

        commaSeparatedList
                = commaSeparatedList.replace("[", "")
                .replace("]", "")
                .replace(" ", ",");
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "www.themealdb.com/api/json/v2/9973533/filter.php?i="+commaSeparatedList);
    }

    public void get10RandomRecipes(final VolleyCallback callback) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/randomselection.php");
    }

    public void getLatestRecipes(final VolleyCallback callback) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/latest.php");
    }

    public void getRecipesByMainIngredient(final VolleyCallback callback, String mainIngredient) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/filter.php?i=" + mainIngredient);
    }

    public void getRecipeById(final VolleyCallback callback, int id) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/lookup.php?i=" + id);
    }

    public void getRecipesByCategory(final VolleyCallback callback, String category) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/filter.php?c=" + category);
    }

    public void getRecipeByName(final VolleyCallback callback, String name) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/search.php?s="+name);
    }

    public void getRecipesByCuisine(final VolleyCallback callback, String cuisine) {
        getData(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Handle API response
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                // Handle error response
                callback.onFailure(error);
                error.printStackTrace();
            }
        }, "https://www.themealdb.com/api/json/v2/9973533/filter.php?a="+cuisine);
    }

    private void getData(final VolleyCallback callback, String url) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle API response
                        callback.onSuccess(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        callback.onFailure(error);
                    }
                });
        queue.add(request);

    }

}
