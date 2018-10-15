package com.example.arshu.lab04;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        BatteryNotifier receiver = new BatteryNotifier();
        Intent batteryStatus = registerReceiver(receiver, filter);


    }
}
