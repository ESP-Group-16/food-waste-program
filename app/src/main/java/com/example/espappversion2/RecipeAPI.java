package com.example.espappversion2;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

// Ok so this API actually goes hard
// Have a look at the documentation https://www.themealdb.com/api.php
// let's hope it works :)

/*

    the response structure of the API is a json of the format:
    
    "meals": {

               "idemeal": int,
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

            }

 */
public class RecipeAPI {

    private Context mContext;
    public RecipeAPI(Context mContext) {
        this.mContext = mContext;
    }

    public void getData(final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(mContext);

        String url = "https://www.themealdb.com/api/json/v1/1/search.php?f=a";
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
                }) {

        };
        queue.add(request);

    }

}
