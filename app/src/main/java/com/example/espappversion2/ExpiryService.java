package com.example.espappversion2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import static java.security.AccessController.getContext;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
// this class is used for checking if food is about to expire and send a notification
public class ExpiryService extends IntentService {
    private User currentUser;
    private String CHANNEL_ID = "EXPIRY_NOTIFICATION";

    public ExpiryService() {
        super("ExpiryService");  // allegedly deprecated, if it works, then it can respectfully go away :)
        currentUser = Utils.getInstance(this).getCurrentUser();

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // check expiry dates here
        ArrayList<Stock> fridgePantryItems = Utils.getInstance(this).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[0]);
        ArrayList<Stock> freezerPantryItems = Utils.getInstance(this).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[1]);
        ArrayList<Stock> cupboardPantryItems = Utils.getInstance(this).getPantryByUser(currentUser).getPantryItems().get(Pantry.STORAGE_LOCATIONS[2]);
        ArrayList<Stock> allPantry = new ArrayList<Stock>();
        allPantry.addAll(freezerPantryItems);
        allPantry.addAll(fridgePantryItems);
        allPantry.addAll(cupboardPantryItems);
        boolean alreadySentExpired = false;
        boolean alreadySentAboutToExpire = false;

        NotificationCompat.Builder builderAlreadyExpired = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.pantrypal_icon)
                .setContentTitle("Expired items")
                .setContentText("You have expired items in your pantry")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You have expired items in your pantry"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationCompat.Builder builderWillExpire = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.pantrypal_icon)
                .setContentTitle("Items expiring soon!")
                .setContentText("You have items in your pantry which will go off soon!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You have items in your pantry which will go off soon!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        this.createNotificationChannel();
        startForeground(2, builderWillExpire.build());
        startForeground(1, builderAlreadyExpired.build());



        for (int i = 0; i < allPantry.size(); i++) {
            if (allPantry.get(i).checkExpired() && !alreadySentExpired) {
                alreadySentExpired = true;
                // send notification about expired items

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1,  builderAlreadyExpired.build());
            }

            if (getDaysInFuture(allPantry.get(i).getExpiresAt()) < 2 && !alreadySentAboutToExpire) {
                alreadySentAboutToExpire = true;
                // send notification about items soon to expire

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(2,  builderWillExpire.build());
            }

        }
        Log.i("ExpiryService", "Service running");
    }

    private static int getDaysInFuture(String date) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate storedDate = LocalDate.parse(date, formatter);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Calculate the difference in days
            long daysDifference = ChronoUnit.DAYS.between(currentDate, storedDate);

            return (int) daysDifference;
        }
        return 0;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Expiry Notification";
            String description = "Expiry Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
