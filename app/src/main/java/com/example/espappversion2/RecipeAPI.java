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
