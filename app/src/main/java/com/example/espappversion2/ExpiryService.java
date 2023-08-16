package com.example.espappversion2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

public class ExpiryService extends IntentService {

    public ExpiryService(User currentUser) {
        super("ExpiryService");  // allegedly deprecated, if it works, then it can respectfully go away :)
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // check expiry dates here
        ArrayList<Stock> fridgePantryItems = Utils.getInstance(this).getPantryByUser().getPantryItems().get(Pantry.STORAGE_LOCATIONS[0]);
        System.out.println("Fridge items in PantryFragment: " + fridgePantryItems);
        ArrayList<Stock> freezerPantryItems = Utils.getInstance(getActivity()).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[1]);
        ArrayList<Stock> cupboardPantryItems = Utils.getInstance(getActivity()).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[2]);
        Log.i("ExpiryService", "Service running");
    }
}
