package com.example.arshu.lab04;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;

public class BatteryNotifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String note = "";
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        boolean isFull = status == BatteryManager.BATTERY_STATUS_FULL;
        if (!isFull){
            if (isCharging)
                note += "Battery is charging.";
            else
                note += "Battery is not charging.";
        }
        else{
            note += "Battery is full.";
        }

        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        boolean isHealthy = health == BatteryManager.BATTERY_HEALTH_GOOD;
        if(isHealthy){
            note += " Battery is healthy.";
        }
        else{
            if (health == BatteryManager.BATTERY_HEALTH_COLD)
                note += " Battery is cold.";
            else if (health == BatteryManager.BATTERY_HEALTH_DEAD)
                note += " Battery is dead";
            else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE)
                note += " Battery is over voltage.";
            else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT)
                note += " Battery is over heating.";
            else if (health == BatteryManager.BATTERY_HEALTH_UNKNOWN)
                note += " Battery is unknown.";
            else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE)
                note += " Battery has unspecified failure.";

        }
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        if (usbCharge)
            note += " Power source is USB.";
        if (acCharge)
            note += " Power source is AC adapter.";
        int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
        note = note + " The temperature of the battery is " + temp;

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(note))
                        .setContentTitle("")
                        .setContentText(note);
        // Sets an ID for the notification
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
