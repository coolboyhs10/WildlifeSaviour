package com.example.codehead.criminalintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d("Test", "Brod");
        Log.d("startuptest", "StartUpBootReceiver " + intent.getAction());

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, SyncService.class);

            context.startService(pushIntent);
        }
    }
}