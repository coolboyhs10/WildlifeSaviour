package com.example.codehead.criminalintent;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;
    Button mSOSButton,mReportCrimeButton,mViewCrimesButton;
    ImageButton rewardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        requestPermission();
        mSOSButton=findViewById(R.id.sosbtn);
//        mReportCrimeButton=findViewById(R.id.reportcrimebtn);
        mViewCrimesButton=findViewById(R.id.viewcrimebtn);
        rewardButton = findViewById(R.id.reward);

        mViewCrimesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,CrimeListActivity.class);
                startActivity(i);
            }
        });

/*        mReportCrimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,CrimeActivity.class);
                startActivity(i);
            }
        });*/

        mSOSButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage("+919716773684", null, "Emergency! Wildlife in danger at location \n lat:23.636231\n long: 77.343222", pi,null);

                Toast.makeText(getApplicationContext(),
                        "SENT!!", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,RewardActivity.class);
                startActivity(i);
            }
        });

    }


    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }



}
