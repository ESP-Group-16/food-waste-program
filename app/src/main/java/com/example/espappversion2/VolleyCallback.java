package com.example.espappversion2;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject response);
    void onFailure(VolleyError error);
}
