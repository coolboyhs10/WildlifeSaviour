package com.example.codehead.criminalintent;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;


public class SyncService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Handler mHandler;
    // default interval for syncing data
//    public static final long DEFAULT_SYNC_INTERVAL = 24*60*60 * 1000;

    public static final long DEFAULT_SYNC_INTERVAL = 20 * 1000;


    // task to be run here
    private Runnable runnableService = new Runnable() {
        @Override
        public void run() {
            syncData();
            // Repeat this runnable code block again every ... min
            mHandler.postDelayed(runnableService, DEFAULT_SYNC_INTERVAL);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(SyncService.this, "IN Start", Toast.LENGTH_LONG).show();

        // Create the Handler object
        mHandler = new Handler();
        // Execute a runnable task as soon as possible
        mHandler.post(runnableService);

        return START_STICKY;
    }

    private synchronized void syncData() {
        // call your rest service here
        Toast.makeText(SyncService.this, "IN Sync", Toast.LENGTH_LONG).show();
    }




}
