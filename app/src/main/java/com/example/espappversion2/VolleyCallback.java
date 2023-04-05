package com.example.espappversion2;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject response)throws JSONException;
    void onFailure(VolleyError error);
}
