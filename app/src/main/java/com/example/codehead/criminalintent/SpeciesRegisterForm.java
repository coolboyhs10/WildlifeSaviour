package com.example.codehead.criminalintent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.codehead.criminalintent.database.DiaryDBHelper;

import java.util.Calendar;
import java.util.Random;

import static com.example.codehead.criminalintent.MainActivity.FLORA;
import static com.example.codehead.criminalintent.MainActivity.TYPE;

public class SpeciesRegisterForm extends AppCompatActivity {
    private String sp_type;

    TextView header;
    EditText local_name, age, description, area, lat, lang, photo, video;
    Spinner common_name, prev_seen, gender, growth, health, death;

    Button btnDatePicker, btnTimePicker, b_save;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_register_form);

        diaryDBHelper = new DiaryDBHelper(this);

        Intent intent = getIntent();
        sp_type = intent.getStringExtra(TYPE);

        local_name= findViewById(R.id.local_name);
        age= findViewById(R.id.age);
        description= findViewById(R.id.description);
        lat= findViewById(R.id.lat);
        lang= findViewById(R.id.lon);
        area= findViewById(R.id.area_name);
        common_name= findViewById(R.id.common_name);
        prev_seen= findViewById(R.id.prev_seen);
        gender= findViewById(R.id.gender);
        health= findViewById(R.id.health);
        growth= findViewById(R.id.growth);
        death= findViewById(R.id.death_cause);
        header = findViewById(R.id.heading);

        b_save = findViewById(R.id.b_save);

        final ArrayAdapter<String> healthAdapter;

        if(sp_type.contentEquals(FLORA)) {
            header.setText("Flora Diary");

            ArrayAdapter<String> commonNameAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.flora_common_name));
            common_name.setAdapter(commonNameAdapter);


            healthAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.flora_health));
            health.setAdapter(healthAdapter);


            ArrayAdapter<String> deathAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.flora_death));
            death.setAdapter(deathAdapter);

        } else {
            header.setText("Fauna Diary");


            ArrayAdapter<String> commonNameAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.fauna_common_name));
            common_name.setAdapter(commonNameAdapter);


            healthAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.fauna_health));
            health.setAdapter(healthAdapter);


            ArrayAdapter<String> deathAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.fauna_death));
            death.setAdapter(deathAdapter);

            growth.setVisibility(View.GONE);
        }


        // death spinner
        death.setVisibility(View.GONE);
        health.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position == healthAdapter.getCount()-1) {
                    death.setVisibility(View.VISIBLE);
                } else {
                    death.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        btnDatePicker= findViewById(R.id.date_pck);
        btnTimePicker= findViewById(R.id.time_pck);
        txtDate= findViewById(R.id.t_date);
        txtTime= findViewById(R.id.t_time);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(SpeciesRegisterForm.this, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog( SpeciesRegisterForm.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                }
            });


        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save data offline
                int officer_id = (new Random()).nextInt();
                String date_time = txtDate.getText().toString() +", "+txtTime.getText().toString();
                String type = sp_type;
                int species_id = (int) common_name.getSelectedItemId()+1;
                String local_n = local_name.getText().toString();
                String prev = prev_seen.getSelectedItem().toString();
                String gender_cont = gender.getSelectedItem().toString();
                int age_cont = Integer.parseInt(age.getText().toString());
                String health_cont = health.getSelectedItem().toString();
                String death_cont = death.getSelectedItem().toString();
                String desc = description.getText().toString();
                String area_c = area.getText().toString();
                double lati = Double.parseDouble(lat.getText().toString());
                double langi = Double.parseDouble(lang.getText().toString());
                String photo_link = "None";
                String video_link = "None";

                int count = 1;

                String growth_cont = "NA";
                if(sp_type.contentEquals(FLORA)) {
                    growth_cont = growth.getSelectedItem().toString();
                }

                boolean insertData = diaryDBHelper.addData(date_time, officer_id, type, species_id, local_n, prev, count, gender_cont,
                        age_cont, health_cont, death_cont, growth_cont, desc, area_c, lati, langi, photo_link, video_link);

                if(insertData) {
                    Toast.makeText(SpeciesRegisterForm.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    Intent newintent = new Intent(SpeciesRegisterForm.this, DiaryActivity.class);
                    startActivity(newintent);
                }
                else
                    Toast.makeText(SpeciesRegisterForm.this, "Try again!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
