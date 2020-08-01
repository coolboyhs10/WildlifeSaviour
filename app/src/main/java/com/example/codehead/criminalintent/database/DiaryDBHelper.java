package com.example.codehead.criminalintent.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.date;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.growth_status;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.local_name;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.officer_id;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.id;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.age;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.age;

import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.age;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.area_name;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.count;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.death_cause;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.description;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.gender;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.health_status;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.latitude;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.longitude;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.photo_link;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.previously_seen;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.species_id;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.type;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.Cols.video_link;
import static com.example.codehead.criminalintent.database.DiaryDbSchema.DiaryTable.NAME;

public class DiaryDBHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DATABASE_NAME="diary.db";

    public DiaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("create table "+ NAME+"("+
                id + " INTEGER primary key autoincrement,"+
                date + " datetime NOT NULL," +
                officer_id + " INTEGER NOT NULL," +
                local_name + " TEXT," +
                type + " TEXT," +
                species_id + " INTEGER NOT NULL," +
                previously_seen + " TEXT NOT NULL," +
                count + " INTEGER," +
                gender +" TEXT," +
                age +" INTEGER," +
                growth_status+" TEXT," +
                health_status+" TEXT," +
                death_cause +" TEXT," +
                description +" TEXT," +
                area_name +" TEXT NOT NULL," +
                latitude +" REAL," +
                longitude +" REAL," +
                photo_link +" TEXT," +
                video_link +" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(String i_date, int i_officer_id, String i_type, int i_species_id, String local_n, String i_prev_seen, int i_count, String i_gender, int i_age, String i_health, String i_death, String i_growth, String i_description, String i_area, double i_lat, double i_lang, String i_photo, String i_video) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(date, i_date);
        contentValues.put(officer_id, i_officer_id);
        contentValues.put(type, i_type);
        contentValues.put(species_id, i_species_id);
        contentValues.put(local_name, local_n);
        contentValues.put(previously_seen, i_prev_seen);
        contentValues.put(count, i_count);
        contentValues.put(gender, i_gender);
        contentValues.put(age, i_age);
        contentValues.put(health_status, i_health);
        contentValues.put(death_cause, i_death);
        contentValues.put(growth_status, i_growth);
        contentValues.put(description, i_description);
        contentValues.put(area_name, i_area);
        contentValues.put(latitude, i_lat);
        contentValues.put(longitude, i_lang);
        contentValues.put(photo_link, i_photo);
        contentValues.put(video_link, i_video);


        long result = db.insert(NAME, null, contentValues);

        Log.d("db", "addData: Adding " + Double.toString(result));

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

//    public Cursor getActiveSpecies(){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        final Calendar c = Calendar.getInstance();
//        int mMonth = c.get(Calendar.MONTH);

//        String query = "SELECT * FROM " + NAME + " WHERE date";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }




}
