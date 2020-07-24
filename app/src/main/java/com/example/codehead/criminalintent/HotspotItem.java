package com.example.codehead.criminalintent;

import com.google.android.gms.maps.model.LatLng;

public class HotspotItem {
    private String imageUrl;
    private String speciesName;
    private LatLng location;

    public  HotspotItem(String imageUrl, String speciesName, LatLng location) {
        this.imageUrl = imageUrl;
        this.speciesName = speciesName;
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public LatLng getLocation() {
        return location;
    }
}
