package com.example.codehead.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CrimeItemsListFragment extends Fragment implements CrimeItemAdapter.OnItemClickListener{
    public static final String EXTRA_NAME = "speciesName";
    public static final String EXTRA_LATITUTE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";

    private RecyclerView recyclerView;
    private CrimeItemAdapter crimeItemAdapter;
    private ArrayList<Crime> crimeArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_crime_items_fragment, container, false);


        recyclerView = rootView.findViewById(R.id.crime_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        crimeArrayList = new ArrayList<>();

        getData();

        return rootView;
    }



    // calling api for data
    private void getData() {
        AndroidNetworking.get("http://sih-location-api.herokuapp.com/getvictim")
                .addQueryParameter("month", "4")
//                .addHeaders("token", "1234")
                .setTag("GetData")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        for (int i = 0; i < response.length(); ++i) {
                            try {
                                JSONObject crime = response.getJSONObject(i);

                                String area = crime.getString("Area");
                                String crimeName = crime.getString("Crime");
                                String date = crime.getString("Date");
                                String type = crime.getString("Flora/Fauna");
                                String species_name = crime.getString("Species Name");
                                Double Latitude = crime.getDouble("Latitude");
                                Double Longitude = crime.getDouble("Longitude");

                                crimeArrayList.add(new Crime(crimeName, area, date, type, new LatLng(Latitude, Longitude), species_name));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        crimeItemAdapter = new CrimeItemAdapter(getActivity(), crimeArrayList);
                        recyclerView.setAdapter(crimeItemAdapter);
                        crimeItemAdapter.setOnItemClickListener(CrimeItemsListFragment.this);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

    }


    @Override
    public void onItemClick(int posistion) {
        Intent mapintent = new Intent(getActivity(), MapsActivity.class);
        Crime  clickedItem = crimeArrayList.get(posistion);
        mapintent.putExtra(EXTRA_NAME, clickedItem.getCrimeName());
        mapintent.putExtra(EXTRA_LATITUTE, clickedItem.getLocation().latitude);
        mapintent.putExtra(EXTRA_LONGITUDE, clickedItem.getLocation().longitude);

        startActivity(mapintent);
    }
}
