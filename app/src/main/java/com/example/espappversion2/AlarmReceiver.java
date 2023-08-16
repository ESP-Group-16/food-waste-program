package com.example.espappversion2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// this class receives an "alarm" to wake up periodically and check for expiry dates
// once it is woken up from MainActivity, it calls expiryService and if there are items expiring within 24 hours, it will notify

public class AlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "hmm";

    @Override
    public void onReceive(Context context, Intent intent) {
        // https://developer.android.com/develop/ui/views/notifications/build-notification
        Intent i = new Intent(context, ExpiryService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}
