package com.example.codehead.criminalintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class admin_main_activity extends AppCompatActivity {

    private ImageButton divisions;
    private ImageButton vulnerable_entities;
    private ImageButton criminals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_activity);
        divisions = (ImageButton) findViewById(R.id.divisions);
        vulnerable_entities = (ImageButton) findViewById(R.id.vulnerable_entities);
        criminals = (ImageButton) findViewById(R.id.criminals);

        divisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Divisions", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(admin_main_activity.this, divisional_officers_list_activity.class);
                startActivity(intent);
            }
        });

        vulnerable_entities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "vulnerable_entities", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(admin_main_activity.this, HotspotsTabActivity.class);
                startActivity(intent);
            }
        });

        criminals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "criminals", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
