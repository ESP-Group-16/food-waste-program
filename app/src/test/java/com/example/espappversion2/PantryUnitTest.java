package com.example.espappversion2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class PantryUnitTest {

    private Repository repo;
    private Stock stockitem;
    private Food food;

    @Before
    public void setUp() {
        this.stockitem = new Stock();
        this.food = new Food();
        this.repo = new Repository();
        repo.source = new Datasource();
    }


    @Test
    public void newPantryShouldBeEmpty() {
        assertEquals(0, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).size());
        assertEquals(0, Objects.requireNonNull(repo.source.
                PantryInformation.get("fridge")).size());
        assertEquals(0, Objects.requireNonNull(repo.source.
                PantryInformation.get("freezer")).size());
    }

    @Test
    public void addItemToPantry() {
        food.setName("TestAdding");
        stockitem.setFood(food);

        repo.addStockItem("cupboard", stockitem);

        repo.viewAllPantry();
        assertEquals("TestAdding", Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).get(0).getFood().getName());
    }

    // TODO: Might need a teardown function to remove the added item

    @Test
    public void addandremoveItemFromPantry() {
        food.setName("TestAdding");
        stockitem.setFood(food);

        repo.addStockItem("cupboard", stockitem);
        assertEquals("TestAdding", Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).get(0).getFood().getName());

        repo.removeStockItem("cupboard", stockitem);

        repo.viewAllPantry();

        ArrayList<Stock> compareitem = new ArrayList<>();
        assertEquals(compareitem, repo.source.PantryInformation.get("cupboard"));
    }

    @Test
    public void removeFromEmptyPantry() {
        food.setName("TestAdding");
        stockitem.setFood(food);

        // Doesn't throw exception - instead returns false: is caught by try catch.
//        assertThrows(IllegalArgumentException.class,
//                () -> {
//                    repo.removeStockItem("cupboard", stockitem);
//                });

        assertFalse(repo.removeStockItem("cupboard", stockitem));
    }

    // adding items that are already in the pantry should add the quantity
    // i.e. 1kg of chocolate is already in the cupboard
    // we then add another 2kg of chocolate, we should have 3kg now rather than 2 separate entries
    @Test
    public void addingDuplicateShouldAddUpQty() {
        food.setName("TestAdding");
        stockitem.setFood(food);
        stockitem.setQuantity(2.5);


        assertEquals(0, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).size());

        repo.addStockItem("cupboard", stockitem);
        // testing adding was successful
        assertEquals("TestAdding", Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).get(0).getFood().getName());

        repo.addStockItem("cupboard", stockitem);
        repo.viewAllPantry();
        // asserting there should only be one of the same food entry
        assertEquals(1, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).size());
        // asserting they added up together
        assertEquals(5.0, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).get(0).getQuantity());

    }

}
