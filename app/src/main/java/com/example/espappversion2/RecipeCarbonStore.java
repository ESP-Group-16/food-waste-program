package com.example.espappversion2;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeCarbonStore {
    private String json = "{\"meals\":[{\"strCategory\":\"Beef\"},{\"strCategory\":\"Breakfast\"},{\"strCategory\":\"Chicken\"},{\"strCategory\":\"Dessert\"},{\"strCategory\":\"Goat\"},{\"strCategory\":\"Lamb\"},{\"strCategory\":\"Miscellaneous\"},{\"strCategory\":\"Pasta\"},{\"strCategory\":\"Pork\"},{\"strCategory\":\"Seafood\"},{\"strCategory\":\"Side\"},{\"strCategory\":\"Starter\"},{\"strCategory\":\"Vegan\"},{\"strCategory\":\"Vegetarian\"}]}";
    Gson gson = new Gson();
    JSONObject convertedJson = gson.fromJson(json, JSONObject.class);

    private ArrayList<String> categories = RecipeAPI.convertJSONCategoriesToArrList(convertedJson);


    public RecipeCarbonStore() throws JSONException {
    }

}
