//package com.example.espappversion2;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertThrows;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class PantryUnitTest {
//
//    private final Repository repo = new Repository();
//
//    @Test
//    public void addItemToPantry(){
//
//        Stock stockitem = new Stock();
//        Food food = new Food();
//        food.setName("TestAdding");
//        stockitem.setFood(food);
//
//        repo.addStockItem("cupboard", stockitem);
//
//        repo.viewAllPantry();
//        assertEquals("TestAdding", Objects.requireNonNull(Datasource.getInstance().
//                PantryInformation.get("cupboard")).get(0).getFood().getName());
//    }
//
//
//    @Test
//    public void addandremoveItemFromPantry(){
//
//        Stock stockitem = new Stock();
//        Food food = new Food();
//        food.setName("TestAdding");
//        stockitem.setFood(food);
//
//        repo.addStockItem("cupboard", stockitem);
//        assertEquals("TestAdding", Objects.requireNonNull(Datasource.getInstance().
//                PantryInformation.get("cupboard")).get(0).getFood().getName());
//
//        repo.removeStockItem("cupboard", stockitem);
//
//        repo.viewAllPantry();
//
//        ArrayList<Stock> compareitem = new ArrayList<>();
//        assertEquals(compareitem, Datasource.getInstance().PantryInformation.get("cupboard"));
//    }
//
//    @Test
//    public void removeFromEmptyPantry() {
//
//        Stock stockitem = new Stock();
//        Food food = new Food();
//        food.setName("TestAdding");
//        stockitem.setFood(food);
//
//        // Doesn't throw exception - instead returns false: is caught by try catch.
////        assertThrows(IllegalArgumentException.class,
////                () -> {
////                    repo.removeStockItem("cupboard", stockitem);
////                });
//
//        assertFalse(repo.removeStockItem("cupboard", stockitem));
//    }
//
//}
