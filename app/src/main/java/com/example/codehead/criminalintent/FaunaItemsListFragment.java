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

public class FaunaItemsListFragment extends Fragment implements HotspotItemAdapter.OnItemClickListener{
    public static final String EXTRA_NAME = "speciesName";
    public static final String EXTRA_LATITUTE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";


    private RecyclerView recyclerView;
    private HotspotItemAdapter hotspotItemAdapter;
    private ArrayList<HotspotItem> hotspotItemArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fauna_items_fragment, container, false);


        recyclerView = rootView.findViewById(R.id.hotspot_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        hotspotItemArrayList = new ArrayList<>();

        getData();

        return rootView;
    }



    // calling api for data
    private void getData() {
        AndroidNetworking.get("http://sih-location-api.herokuapp.com/getfauna")
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
                                JSONObject hotspot = response.getJSONObject(i);

                                //String imageUrl = "https://res.cloudinary.com/tbmg/image/upload/c_scale,w_400,f_auto,q_auto/v1563980020/tb/articles/2019/blog/TB-Blog-072419-Butterfly.jpg";
                                String imageUrl = "photo_link";
                                String speciesName = hotspot.getString("Common_name");
                                int priority = hotspot.getInt("priority");
                                int status = hotspot.getInt("status");
                                String family = hotspot.getString("Scientific_name");
                                int endemics = hotspot.getInt("Endemic");
                                String sites = hotspot.getString("Location");
                                Double Latitude = hotspot.getDouble("Latitude");
                                Double Longitude = hotspot.getDouble("Longitude");

                                hotspotItemArrayList.add(new HotspotItem(1, imageUrl, new LatLng(Latitude, Longitude), speciesName, sites, endemics, priority, status, family));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        hotspotItemAdapter = new HotspotItemAdapter(getActivity(), hotspotItemArrayList);
                        recyclerView.setAdapter(hotspotItemAdapter);
                        hotspotItemAdapter.setOnItemClickListener(FaunaItemsListFragment.this);
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
        HotspotItem  clickedItem = hotspotItemArrayList.get(posistion);
        mapintent.putExtra(EXTRA_NAME, clickedItem.getSpeciesScientificName());
        mapintent.putExtra(EXTRA_LATITUTE, clickedItem.getLocation().latitude);
        mapintent.putExtra(EXTRA_LONGITUDE, clickedItem.getLocation().longitude);

        startActivity(mapintent);
    }
}
