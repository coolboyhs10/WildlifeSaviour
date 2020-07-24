package com.example.codehead.criminalintent;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.codehead.criminalintent.HotspotListActivity.EXTRA_LATITUTE;
import static com.example.codehead.criminalintent.HotspotListActivity.EXTRA_LONGITUDE;
import static com.example.codehead.criminalintent.HotspotListActivity.EXTRA_NAME;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final Double DEFAULT_LAT = 11.7401;
    private final Double DEFAULT_LONG = 92.658;

    private GoogleMap mMap;
    private String speciesName;
    private Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        speciesName = intent.getStringExtra(EXTRA_NAME);
        lat = intent.getDoubleExtra(EXTRA_LATITUTE, DEFAULT_LAT);
        lon = intent.getDoubleExtra(EXTRA_LONGITUDE, DEFAULT_LONG);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(lat, lon);

        //adding marker
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String timeStamp = dateFormat.format(new Date());

        mMap.addMarker(new MarkerOptions().position(pos).title("Name: " + speciesName));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 16.0f));

    }
}
