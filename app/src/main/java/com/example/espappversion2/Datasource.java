package com.example.espappversion2;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class Datasource {
    //stores all the hard coded data
    private static Datasource instance;
    ArrayList<String> dietaryInfoR1= new ArrayList<String>();
    ArrayList<Ingredient> ingredientsR1= new ArrayList<Ingredient>();
    ArrayList<String> stepsR1= new ArrayList<String>();
    ArrayList<String> categoryR1= new ArrayList<String>();
    User creatorR1=new User();
    Recipe R1= new Recipe(0,"stirfry","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R2= new Recipe(1,"curry","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R3=new Recipe(2,"soup","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    Recipe R4=new Recipe(3,"pasta","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);
    int currentuserID;
    ArrayList<Recipe> AllRecipes=new ArrayList<Recipe>();
    ArrayList<User> AllUsers=new ArrayList<User>();
    ArrayList<Stock> AllStock=new ArrayList<Stock>();
    ArrayList<Food> AllFood=new ArrayList<Food>();
    ArrayList<Ingredient> AllIngredients=new ArrayList<Ingredient>();
    ArrayList<Pantry> AllPantry=new ArrayList<Pantry>();
    String test="bad";

    // Ryan's special allergio extrodinario
    // TODO: Adapt into User details once the user side of things has figured itself out.
    HashMap<String, Boolean> AllergyInformation = new HashMap<>();


    // Ryan's special pantrino extrodinario
    // TODO: Adapt into PANTRY details once the PANTRY side of things has figured itself out.
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
