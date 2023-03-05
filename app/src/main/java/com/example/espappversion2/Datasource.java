package com.example.espappversion2;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Datasource {
    //stores all the hard coded data
    ArrayList<String> dietaryInfoR1= new ArrayList<String>();
    ArrayList<Ingredient> ingredientsR1= new ArrayList<Ingredient>();
    ArrayList<String> stepsR1= new ArrayList<String>();
    ArrayList<String> categoryR1= new ArrayList<String>();
    User creatorR1=new User();
    Recipe R1= new Recipe(0,"stirfry","fakeurl",10,dietaryInfoR1,ingredientsR1,stepsR1,categoryR1,creatorR1);

    ArrayList<Recipe> AllRecipes=new ArrayList<Recipe>();
    ArrayList<User> AllUsers=new ArrayList<User>();
    ArrayList<Stock> AllStock=new ArrayList<Stock>();
    ArrayList<Food> AllFood=new ArrayList<Food>();
    ArrayList<Ingredient> AllIngredients=new ArrayList<Ingredient>();
    ArrayList<Pantry> AllPantry=new ArrayList<Pantry>();

    void start(){
        AllRecipes.add(R1);
    }
}
