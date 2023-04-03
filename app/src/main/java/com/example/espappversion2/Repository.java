package com.example.espappversion2;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Repository {
    //used for getting data and string data to and from Datasource
    private Datasource source = Datasource.getInstance();
    private SharedPreferences sharedPreferences;
    private Context context;

    public Repository(Context context) {
        this.context = context;
    }

    /////////////////////////////////////////////////// for parital string name search
    public ArrayList<Recipe> GetRecipeContainingName(String name){
        ArrayList<Recipe> RecipesOut=new ArrayList<>();
        for(int i=0;i<source.AllRecipes.size();i++) {
            Recipe currentobject = source.AllRecipes.get(i);
            if (name.equals(currentobject.getName())) {
                RecipesOut.add(currentobject);
            }
        }
        return RecipesOut;
    }
    //////////////////////////////////////////////////////get and set current user
    public int GetCurrentUserID(){
        return source.currentuserID;
    }
    public void SetCurrentUserID(int ID){
        source.currentuserID=ID;
    }
    //////////////////////////////////////////////////////all GetFromName functions
    public User GetUserFromName(String name){
        User user=null;
        for(int i=0;i<source.AllUsers.size();i++){
            User currentobject= source.AllUsers.get(i);
            if (name.equals(currentobject.getUserName())){
                user=currentobject;
            }
        }
        //dehash here
        return user;
    }
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

    public ArrayList<Recipe> GetFavouriteRecipes() {
        return source.FavouriteRecipes;
    }

    public void addRecipeToFavourites(Recipe recipe) {
        source.FavouriteRecipes.add(recipe);
    }

    public void removeRecipeFromFavourites(Recipe recipe) {
        source.FavouriteRecipes.remove(recipe);
    }

    public boolean containsRecipeInFavourites(Recipe recipe) {
        return source.FavouriteRecipes.contains(recipe);
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
        user.setUserId(source.AllUsers.size());
        //hashpassword
        source.AllUsers.add(user);
    }
    public void StoreRecipe(Recipe recipe){
        recipe.setRecipeId(source.AllRecipes.size());
        source.AllRecipes.add(recipe);
    }
    public void StorePantry(Pantry pantry){
        pantry.setPantryId(source.AllPantry.size());
        source.AllPantry.add(pantry);
    }
    public void StoreStock(Stock stock){
        source.AllStock.add(stock);
    }
    public void StoreFood(Food food){
        food.setFoodId(source.AllFood.size());
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
        try {
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
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAllergy(String allergykey) { // returns true/false for success/ failure
        try {
            if (allergykey == null) { // key is null (null not stored in hashmap)
                throw new IllegalArgumentException("Argument is null. Cannot remove null from Allergy HashMap");
            } else {
                source.AllergyInformation.put(allergykey, true);
                return true;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getAllergies(){

        ArrayList<String> temp = new ArrayList<>();

        for (String i : source.AllergyInformation.keySet()){
            if (Boolean.TRUE.equals(source.AllergyInformation.get(i))){ // If the boolean is true
                temp.add(i);
            }
        }

        return temp;
    }

    public void viewAllergyList(){
        System.out.println(source.AllergyInformation);
    } // TODO: Remove Debugging method


    // returns shopping list, which a list of lists with items separated based on location
    // 0: fridge, 1: freezer, 2: cupboard
    public ArrayList<ArrayList<Stock>> getShoppingList() {
        return source.shoppingList;
    }

    // returns the whole shopping list as one list
    public ArrayList<Stock> getEntireShoppingList() {
        ArrayList<Stock> shoppingList = new ArrayList<>();
        for(ArrayList<Stock> list : getShoppingList()) {
            shoppingList.addAll(list);
        }
        return shoppingList;
    }

    // i is the storage location - 0: fridge, 1: freezer, 2: cupboard
    public ArrayList<Stock> getShoppingListByLocation(int i) {
        if(0 <= i && i <= 2) {
            return getShoppingList().get(i);
        }
        return null;
    }

    public boolean isShoppingListEmpty() {
        return getShoppingListByLocation(0).isEmpty() && getShoppingListByLocation(1).isEmpty() && getShoppingListByLocation(2).isEmpty();
    }

    // i is the storage location - 0: fridge, 1: freezer, 2: cupboard
    public void addItemToShoppingList(Stock item, int i) {
        source.shoppingList.get(i).add(item);
    }

    // i is the storage location - 0: fridge, 1: freezer, 2: cupboard
    public void removeItemFromShoppingList(Stock item, int i) {
        source.shoppingList.get(i).remove(item);
    }

    // Ryan's special pantrino methodinio extrodinario
    // TODO: Adapt into PANTRY details once the PANTRY side of things has figured itself out.

    // Add Stock Item to x place.
    public boolean addStockItem(String storageloc, Stock stockitem){
        try {
            if (source.PantryInformation.containsKey(storageloc)){ // Storage location exists
                // Gather current Stock info at storage location.
                ArrayList<Stock> temp = source.PantryInformation.get(storageloc);

                // Add new Stock Item and replace old arraylist.
                temp.add(stockitem);
                source.PantryInformation.put(storageloc, temp);
                return true;
            }
            else{ // Storage location doesn't exist
                throw new IllegalArgumentException("Argument storage location does not exist");
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Remove Stock item from x place.
    public boolean removeStockItem(String storageloc, Stock stockitem){
        try {
            if (source.PantryInformation.containsKey(storageloc)){ // Storage location exists
                // Gather current Stock info at storage location.
                ArrayList<Stock> temp = source.PantryInformation.get(storageloc);

                if (temp.contains(stockitem)){ // if stockitem in list: remove it
                    temp.remove(stockitem);
                    source.PantryInformation.put(storageloc, temp);
                }
                else{
                    throw new IllegalArgumentException("Argument stock does not exist to be removed");
                }
                return true;
            }

            else{ // Storage location doesn't exist
                throw new IllegalArgumentException("Argument storage location does not exist");
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean useStockItem(String storageloc, Stock stockitem, double quantityToRemove){
        try {
            if (source.PantryInformation.containsKey(storageloc)){ // Storage location exists
                // Gather current Stock info at storage location.
                ArrayList<Stock> temp = source.PantryInformation.get(storageloc);

                if (temp.contains(stockitem)){ // if stockitem in list: remove it
                    if (stockitem.getQuantity() - quantityToRemove <= 0) {
                        temp.remove(stockitem);
                        source.PantryInformation.put(storageloc, temp);
                    } else {
                        temp.get(temp.indexOf(stockitem)).setQuantity((double) Math.round((stockitem.getQuantity() - quantityToRemove) * 100) / 100);
                        source.PantryInformation.put(storageloc, temp);
                    }
                }
                else{
                    throw new IllegalArgumentException("Argument stock does not exist to be removed");
                }
                return true;
            }

            else{ // Storage location doesn't exist
                throw new IllegalArgumentException("Argument storage location does not exist");
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO: Remove two debugging methods below

    // View Stock Items in x place.
    public void viewPantry(String storageloc){
        System.out.println(storageloc + source.PantryInformation.get(storageloc));
    }

    // View All.
    public void viewAllPantry(){
        System.out.println(source.PantryInformation);
        for (String key: source.PantryInformation.keySet()) {

            System.out.println(key);
            ArrayList<Stock> temp = source.PantryInformation.get(key);
            for (Stock stock: temp
                 ) {
                System.out.print(stock.getFood().getName() + ", ");
            }
            System.out.println();
        }
    }
}
