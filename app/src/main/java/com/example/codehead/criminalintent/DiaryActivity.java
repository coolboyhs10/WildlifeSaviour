package com.example.codehead.criminalintent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.codehead.criminalintent.database.DiaryDBHelper;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    public static final String TYPE = "type";
    public static final String FLORA = "fora";
    public static final String FAUNA = "fauna";

    Button reg_fauna, reg_flora, view;
    DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        diaryDBHelper = new DiaryDBHelper(this);

        view = findViewById(R.id.b_view);
        reg_flora = findViewById(R.id.b_reg_flora);
        reg_fauna = findViewById(R.id.b_reg_fauna);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent newintent = new Intent(DiaryActivity.this, ViewDiaryActivity.class);
                startActivity(newintent);
            }
        });

        reg_fauna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(DiaryActivity.this, SpeciesRegisterForm.class);
                newintent.putExtra(TYPE, FAUNA);
                startActivity(newintent);
            }
        });

        reg_flora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(DiaryActivity.this, SpeciesRegisterForm.class);
                newintent.putExtra(TYPE, FLORA);
                startActivity(newintent);
            }
        });


    }


}
