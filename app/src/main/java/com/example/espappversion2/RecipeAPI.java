package com.example.espappversion2;


// Ok so this API actually goes hard
// Have a look at the documentation https://rapidapi.com/spoonacular/api/recipe-food-nutrition
// This is literally what we need
// let's hope it works :)
public class RecipeAPI {

    RequestQueue queue = Volley.newRequestQueue(this);

    String url = "https://api.example.com/data";
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Handle API response
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error response
                }
            });

}
