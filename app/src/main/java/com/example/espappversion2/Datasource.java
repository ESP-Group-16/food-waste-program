package com.example.espappversion2;

import java.util.ArrayList;
import java.util.HashMap;

public class Datasource {
    //stores all the hard coded data
    private static Datasource instance;
    ArrayList<String> dietaryInfoR1= new ArrayList<String>();
    ArrayList<Ingredient> ingredientsR1= new ArrayList<Ingredient>();
    String stepsR1;
    ArrayList<String> categoryR1= new ArrayList<String>();
    User creatorR1=new User("Bob", "123456");
    Recipe R1= new Recipe(0,"stirfry","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R2= new Recipe(1,"curry","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R3=new Recipe(2,"soup","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R4=new Recipe(3,"pasta","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    int currentuserID;
    ArrayList<Recipe> AllRecipes=new ArrayList<Recipe>();

    ArrayList<Recipe> FavouriteRecipes = new ArrayList<>();
    ArrayList<User> AllUsers=new ArrayList<User>();
    ArrayList<Stock> AllStock=new ArrayList<Stock>();
    ArrayList<Food> AllFood=new ArrayList<Food>();
    ArrayList<Ingredient> AllIngredients=new ArrayList<Ingredient>();
    ArrayList<Pantry> AllPantry=new ArrayList<Pantry>();
    String test="bad";

    // Ryan's special allergio extrodinario
    HashMap<String, Boolean> AllergyInformation = new HashMap<>();


    // Ryan's special pantrino extrodinario
    HashMap<String, ArrayList<Stock>> PantryInformation = new HashMap<String, ArrayList<Stock>>() {{
        put("cupboard", new ArrayList<>());
        put("fridge", new ArrayList<>());
        put("freezer", new ArrayList<>());
    }};


    // shopping list, a list of 3 lists based on storage location - 0: fridge, 1: freezer, 2: cupboard
    ArrayList<ArrayList<Stock>> shoppingList = new ArrayList<>();

    private Datasource() {
        // private constructor
        AllRecipes.add(R1);
        AllRecipes.add(R2);
        AllRecipes.add(R3);
        AllRecipes.add(R4);
        FavouriteRecipes.add(R1);
        shoppingList.add(new ArrayList<>());
        shoppingList.add(new ArrayList<>());
        shoppingList.add(new ArrayList<>());
    }

    public static Datasource getInstance() {//makes datasource a singleton
        if (instance == null) {
            instance = new Datasource();
        }
        return instance;
    }
}
