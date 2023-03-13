package com.example.espappversion2;

import static org.junit.Assert.assertEquals;

import android.content.Intent;

import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowApplication;


public class CreateAccountTest {

    @Test
    public void testSignIn() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();

        String email = "test@example.com";
        String password = "password";

        // call the signIn() method with test credentials
        activity.signIn(email, password);

        // check if the user is navigated to the PantryActivity
        Intent expectedIntent = new Intent(activity, PantryActivity.class);
        expectedIntent.putExtra("email", email);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}