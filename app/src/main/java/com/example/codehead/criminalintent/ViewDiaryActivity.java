package com.example.codehead.criminalintent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codehead.criminalintent.database.DiaryDBHelper;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ViewDiaryActivity extends AppCompatActivity implements DiaryItemAdapter.OnItemClickListener {

    DiaryDBHelper diaryDBHelper;

    private RecyclerView recyclerView;
    private DiaryItemAdapter diaryItemAdapter;
    private ArrayList<DiaryItem> diaryItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary);

        recyclerView = findViewById(R.id.diary_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        diaryItemArrayList = new ArrayList<>();

        diaryDBHelper = new DiaryDBHelper(this);

        Cursor data = diaryDBHelper.getData();
        while(data.moveToNext()){
            //get the value from the database in column 1
            String common_name = data.getString(3);
            String area = data.getString(13);
            String date = data.getString(1);
            String imageUrl = "https://res.cloudinary.com/tbmg/image/upload/c_scale,w_400,f_auto,q_auto/v1563980020/tb/articles/2019/blog/TB-Blog-072419-Butterfly.jpg";

//            diaryConetnt +=  "Date & Time:" + data.getString(1) + " Common name:" + data.getString(3) + " Health:" +
//                    data.getString(10)+" Area:"+ data.getString(13)+"\n\n";

            diaryItemArrayList.add(new DiaryItem(imageUrl, common_name, date, area));

        }


        diaryItemAdapter = new DiaryItemAdapter(ViewDiaryActivity.this, diaryItemArrayList);
        recyclerView.setAdapter(diaryItemAdapter);
        diaryItemAdapter.setOnItemClickListener(ViewDiaryActivity.this);
    }

//
    @Override
    public void onItemClick(int posistion) {
//        Intent mapintent = new Intent(this, MapsActivity.class);
//        HotspotItem  clickedItem = hotspotItemArrayList.get(posistion);
//        mapintent.putExtra(EXTRA_NAME, clickedItem.getSpeciesScientificName());
//        mapintent.putExtra(EXTRA_LATITUTE, clickedItem.getLocation().latitude);
//        mapintent.putExtra(EXTRA_LONGITUDE, clickedItem.getLocation().longitude);
//
//        startActivity(mapintent);
    }


}
