package com.example.espappversion2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NavTests {

    // Tests that the bottom navigation bar will take you to all the pages correctly

    public void testBottomNavBarAllButtons(Class activityParam) {
        // launch app emulator in the selected activity
        ActivityScenario.launch(activityParam);

        // perform a click on the button with ID 'profile'
        onView(withId(R.id.profile)).perform(click());

        // check that the Relative layout with the ID of 'activityProfile' is displayed
        onView(withId(R.id.activityProfile)).check(matches(isDisplayed()));

        ActivityScenario.launch(activityParam);  // Force return to the parameter activity
        onView(withId(R.id.recipe)).perform(click());
        onView(withId(R.id.activityRecipe)).check(matches(isDisplayed()));

        ActivityScenario.launch(activityParam);
        onView(withId(R.id.shoppingList)).perform(click());
        onView(withId(R.id.activityShoppingList)).check(matches(isDisplayed()));

        ActivityScenario.launch(activityParam);
        onView(withId(R.id.pantry)).perform(click());
        onView(withId(R.id.activityPantry)).check(matches(isDisplayed()));

    }

    @Test
    public void testNavPantry() {
        testBottomNavBarAllButtons(PantryActivity.class);
    }

    @Test
    public void testNavProfile() {
        testBottomNavBarAllButtons(ProfileActivity.class);
    }

    @Test
    public void testNavRecipe() {
        testBottomNavBarAllButtons(RecipeActivity.class);
    }

    @Test
    public void testNavShoppingList() {
        testBottomNavBarAllButtons(ShoppingListActivity.class);
    }
}