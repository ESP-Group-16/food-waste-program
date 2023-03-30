package com.example.espappversion2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class AllergyTests {

    // tests to be done:
    // 1. add
    // 2. remove
    // 3. add already existing
    // 4. remove non-existing
    // 5. add non string
    private HashMap<String, Boolean> allergies;
    private Repository repo;

    // Set up an empty map before each test
    @Before
    public void setUp() {
        this.repo = new Repository();
        repo.source = new Datasource();
        this.allergies = repo.source.AllergyInformation;
    }

    // New allergies hashmap should return true for isEmpty
    @Test
    public void testIsEmptyForNewAllergies() {
        repo.viewAllergyList();
        assertTrue(allergies.isEmpty());
    }

    // Adding new items to the hashmap should make it non-empty
    @Test
    public void testAddNoLongerEmpty() {
        repo.addAllergy("peanut");
        assertFalse(allergies.isEmpty());
    }

    // Adding multiple items increments the size of the hashmap
    @Test
    public void testIncrementSizeOnAdd() {
        repo.addAllergy("peanut");
        assertEquals(1, allergies.size());

        repo.addAllergy("milk");
        assertEquals(2, allergies.size());
    }

    // On add, the allergies should be true
    @Test
    public void testAddItemsAreTrue() {
        repo.addAllergy("peanut");
        repo.addAllergy("milk");

        assertEquals(true, allergies.get("peanut"));
        assertEquals(true, allergies.get("milk"));
    }

    // When adding the same key twice, size should not increment
    @Test
    public void testAddNonUnique() {
        repo.viewAllergyList();
        repo.addAllergy("peanut");
        repo.addAllergy("peanut");
        assertEquals(1, allergies.size());
        assertEquals(true, allergies.get("peanut"));
    }

    // Throw exception if removing non-existent item
    @Test(expected= NullPointerException.class)
    public void testThrowsExceptionIfKeyDoesNotExist() {
        repo.addAllergy("peanut");
        repo.removeAllergy("milk");
    }

    // When removing an existing item, it should become false
    @Test
    public void testRemovingMakesFalse() {
        repo.addAllergy("peanut");
        repo.removeAllergy("peanut");
        assertEquals(false, allergies.get("peanut"));
    }

    // When re-adding the same allergy, it becomes true and not added again
    @Test
    public void testRemoveThenAddIsTrue() {
        repo.addAllergy("peanut");
        repo.removeAllergy("peanut");
        repo.addAllergy("peanut");
        assertEquals(true, allergies.get("peanut"));
        assertEquals(1, allergies.size());
    }

    // When adding null, should throw Illegal argument exception
    @Test(expected = IllegalArgumentException.class)
    public void testNullAddThrowsIllegalArgument() {
        repo.addAllergy(null);
    }

    // When removing null, should throw Illegal argument exception
    @Test(expected = IllegalArgumentException.class)
    public void testNullRemoveThrowsIllegalArgument() {
        repo.addAllergy(null);
    }



}