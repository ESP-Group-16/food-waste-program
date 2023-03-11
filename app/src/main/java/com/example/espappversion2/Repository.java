package com.example.espappversion2;

import static android.os.Build.ID;

import java.util.ArrayList;
import java.util.Optional;

public class Repository {
    //used for getting data and string data to and from Datasource
    Datasource source = Datasource.getInstance();

    //////////////////////////////////////////////////////all

    //////////////////////////////////////////////////////all GetFromID functions
    public Recipe GetRecipeFromID(int ID){
        Recipe recipe=null;
        for(int i=0;i<source.AllRecipes.size();i++){
            Recipe currentobject= source.AllRecipes.get(i);
            if (currentobject.getRecipeId()==ID){recipe=currentobject;}
        }
        return recipe;
    }

    public User GetUserFromID(int ID){
        User user=null;
        for(int i=0;i<source.AllUsers.size();i++){
            User currentobject= source.AllUsers.get(i);
            if (currentobject.getUserId()==ID){user=currentobject;}
        }
        return user;
    }

    public Pantry GetPantryFromID(int ID){
        Pantry pantry=null;
        for(int i=0;i<source.AllPantry.size();i++){
            Pantry currentobject= source.AllPantry.get(i);
            if (currentobject.getPantryId()==ID){pantry=currentobject;}
        }
        return pantry;
    }

    public Food GetFoodFromID(int ID){
        Food food=null;
        for(int i=0;i<source.AllFood.size();i++){
            Food currentobject= source.AllFood.get(i);
            if (currentobject.getFoodId()==ID){food=currentobject;}
        }
        return food;
    }

    ////////////////////////////////////////////////////all GetAll Functions
    public ArrayList<User> GetAllUsers(){
        return source.AllUsers;
    }
    public ArrayList<Recipe> GetAllRecipes(){
        return source.AllRecipes;
    }
    public ArrayList<Pantry> GetAllPantry(){
        return source.AllPantry;
    }
    public ArrayList<Stock> GetAllStock(){
        return source.AllStock;
    }
    public ArrayList<Food> GetAllFood(){
        return source.AllFood;
    }
    public ArrayList<Ingredient> GetAllIngredients(){
        return source.AllIngredients;
    }
    ////////////////////////////////////////////////////all Store functions
    public void StoreUser(User user){
        source.AllUsers.add(user);
    }
    public void StoreRecipe(Recipe recipe){
        source.AllRecipes.add(recipe);
    }
    public void StorePantry(Pantry pantry){
        source.AllPantry.add(pantry);
    }
    public void StoreStock(Stock stock){
        source.AllStock.add(stock);
    }
    public void StoreFood(Food food){
        source.AllFood.add(food);
    }
    public void StoreIngredient(Ingredient ingredient){
        source.AllIngredients.add(ingredient);
    }


    // Ryan's special allergio methodinio extrodinario
    // TODO: Adapt into User details once the user side of things has figured itself out.
    // Implementation: Once a key is created it is never removed, just switched on and off (can be changed later)
    // I chose to implement it this way since if we have a spinner with all the allergies or want to list them off it also might be easier to list ones the user does not have
    // something something maybe flatmate has peanut allergy and we are cooking for flatmate?
    public boolean removeAllergy(String allergykey) { // returns true if successful.
        if (allergykey == null) { // key is null (null not stored in hashmap)
            throw new IllegalArgumentException("Argument is null. Cannot remove null from Allergy HashMap");
        } else {
            if (source.AllergyInformation.containsKey(allergykey)) { // if the argument key is not null and it exists as an existing key/value pair
                source.AllergyInformation.put(allergykey, false);
                return true;
            } else { // key does not exist in hashmap.
                throw new NullPointerException("Argument does not exist. Cannot remove null argument from Allergy HashMap");
            }
        }
    }

    public boolean addAllergy(String allergykey) { // returns true/false for success/ failure
        if (allergykey == null) { // key is null (null not stored in hashmap)
            throw new IllegalArgumentException("Argument is null. Cannot remove null from Allergy HashMap");
        } else {
            source.AllergyInformation.put(allergykey, true);
            return true;
        }
    }

    public void viewAllergyList(){ // Debugging method
        System.out.println(source.AllergyInformation);
    }

}
