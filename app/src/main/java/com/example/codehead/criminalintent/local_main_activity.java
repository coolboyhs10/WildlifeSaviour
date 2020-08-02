package com.example.codehead.criminalintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class local_main_activity extends AppCompatActivity {

    ImageButton sos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_main_activity);

        sos = (ImageButton) findViewById(R.id.SOS);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(local_main_activity.this, CustomerMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
