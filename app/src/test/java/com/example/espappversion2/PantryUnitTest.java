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


    // We should be able to remove a portion of an item from the pantry
    // i.e. 2 kg of chocolate and we   c o n s u m e   1.8 kg, we should have 0.2 kg left :(
    @Test
    public void removePartialAmount() {
        food.setName("TestAdding");
        stockitem.setFood(food);
        stockitem.setQuantity(2.0);

        Stock itemToRemove = new Stock();
        repo.addStockItem("cupboard", stockitem);
        repo.useStockItem("cupboard", stockitem, 1.8);

        // Assert item hasn't been completely removed
        assertEquals(1, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).size());
        // Assert item has correctly been subtracted
        assertEquals(0.2, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).get(0).getQuantity(), 0.001);

    }

    @Test
    public void removeMoreThanExists() {
        // this shouldn't even be possible because the user cannot input this but testing it anyway
        food.setName("TestAdding");
        stockitem.setFood(food);
        stockitem.setQuantity(2.0);

        Stock itemToRemove = new Stock();
        repo.addStockItem("cupboard", stockitem);
        repo.useStockItem("cupboard", stockitem, 15.0);

        // Assert item has been completely removed
        assertEquals(0, Objects.requireNonNull(repo.source.
                PantryInformation.get("cupboard")).size());

    }

}
