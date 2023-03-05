package com.example.espappversion2;

public class Repository {
    //used for getting data and string data to and from Datasource
    Datasource source=new Datasource();

    public Recipe GetRecipeFromID(int ID){
        Recipe recipe=null;
        for(int i=0;i<source.AllRecipes.size();i++){
            Recipe currentrecipe= source.AllRecipes.get(i);
            if (currentrecipe.getRecipeId()==ID){
                recipe=currentrecipe;
            }
        }
        return recipe;
    }
    void StoreUser(User user){
        source.AllUsers.add(user);
    }
}
