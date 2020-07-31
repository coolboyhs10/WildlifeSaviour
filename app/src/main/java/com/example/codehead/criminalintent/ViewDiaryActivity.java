package com.example.codehead.criminalintent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codehead.criminalintent.database.DiaryDBHelper;

import java.util.ArrayList;

public class ViewDiaryActivity extends AppCompatActivity {

    DiaryDBHelper diaryDBHelper;
    String diaryConetnt;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary);

        diaryDBHelper = new DiaryDBHelper(this);

        content = findViewById(R.id.content);

        diaryConetnt = "";

        Cursor data = diaryDBHelper.getData();
        while(data.moveToNext()){
            //get the value from the database in column 1
            diaryConetnt +=  "Date & Time:" + data.getString(1) + " Common name:" + data.getString(3) + " Health:" +
                    data.getString(10)+" Area:"+ data.getString(13)+"\n\n";
        }

        content.setText(diaryConetnt);


    }


}
