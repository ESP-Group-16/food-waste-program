package com.example.espappversion2;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject response, String resultFor)throws JSONException;
    void onFailure(VolleyError error);
}
