package com.example.codehead.criminalintent;
import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;
    Button hotspots, notificationTestButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        hotspots = findViewById(R.id.b_hotspots);
        notificationTestButton = findViewById(R.id.test);

        // Initialize API Request Object
        AndroidNetworking.initialize(getApplicationContext());

        notificationTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mNotificationId = 001;

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.drawable.logo)
                                .setContentTitle("New Hotspot detected!")
                                .setContentText("Click to view")
                                .setPriority(NotificationCompat.PRIORITY_HIGH);


                // Create pending intent, mention the Activity which needs to be
                //triggered when user clicks on notification(StopScript.class in this case)

                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        new Intent(MainActivity.this, MapsActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


                mBuilder.setContentIntent(contentIntent);

                // Gets an instance of the NotificationManager service
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                // creating notification channel
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mBuilder.setChannelId("com.example.codehead.criminalintent");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            "com.example.codehead.criminalintent",
                            "Wild Devengers",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );
                    if (mNotificationManager != null) {
                        mNotificationManager.createNotificationChannel(channel);
                    }
                }

                // Builds the notification and issues it.
                mNotificationManager.notify(mNotificationId, mBuilder.build());

            }
        });

        hotspots.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(MainActivity.this, HotspotsTabActivity.class);
                startActivity(newintent);
            }
        });

    }

}
