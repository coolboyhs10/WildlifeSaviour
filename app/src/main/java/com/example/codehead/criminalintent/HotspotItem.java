package com.example.codehead.criminalintent;

import com.google.android.gms.maps.model.LatLng;

public class HotspotItem {
    private String imageUrl;
    private String speciesScientificName;
    private int endemic;
    private String family;
    private LatLng location;
    private String sites;
    private int priority;
    private int status;

    public  HotspotItem(String imageUrl, LatLng location, String speciesScientificName, String sites, int endemic, int priority, int status, String family) {
        this.imageUrl = imageUrl;
        this.speciesScientificName = speciesScientificName;
        this.endemic = endemic;
        this.family = family;
        this.location = location;
        this.sites = sites;
        this.priority = priority;
        this.status = status;


    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getSpeciesScientificName() {
        return speciesScientificName;
    }

    public int getPriority() {
        return priority;
    }

    public String getFamily() {
        return family;
    }

    public String getSites() {
        return sites;
    }

    public int getEndemic() {
        return endemic;
    }

    public int getStatus() {
        return status;
    }
}
