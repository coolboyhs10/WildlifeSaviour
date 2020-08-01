package com.example.codehead.criminalintent;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class divisional_officers_list_activity extends AppCompatActivity implements  OfficerItemAdapter.OnItemClickListener{
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    private OfficerItemAdapter officerItemAdapter;
    private ArrayList<officer> officerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisional_officers_list_activity);


        recyclerView = findViewById(R.id.divisional_officers_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        officerArrayList = new ArrayList<>();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will be implemented soon", Toast.LENGTH_SHORT).show();
            }
        });
        getData();
    }

    private void getData(){
        AndroidNetworking.get("http://sih-location-api.herokuapp.com/fetchallofficer")
                //.addQueryParameter("rank", "Regional Officer")
//                .addHeaders("token", "1234")
                .setTag("GetData")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); ++i) {
                            try {
                                JSONObject officerObj = response.getJSONObject(i);
                                String rank = officerObj.getString("rank");
                                String email_id = officerObj.getString("email_id");
                                String name = officerObj.getString("name");
                                if(rank.equals("Regional Officer"))
                                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                                String phone = officerObj.getString("phone");
                                String username = officerObj.getString("username");
                                String pwd = officerObj.getString("pwd");
                                String area_name = officerObj.getString("area_name");
                                String pincode = officerObj.getString("pincode");

                                officerArrayList.add(new officer(rank, email_id, name, phone, username, pwd, area_name, pincode));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        officerItemAdapter = new OfficerItemAdapter(divisional_officers_list_activity.this, officerArrayList);
                        recyclerView.setAdapter(officerItemAdapter);
                        officerItemAdapter.setOnItemClickListener(divisional_officers_list_activity.this);

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Will be implemented soon", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(int posistion) {

    }
}
