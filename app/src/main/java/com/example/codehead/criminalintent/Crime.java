package com.example.codehead.criminalintent;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.UUID;

//this is the model class for crimes
public class Crime {

    private String crimeName;
    private String crimeArea;
    private String crimeDate;
    private String crimeType;
    private LatLng location;
    private String speciesName;

    public Crime(String crimeName, String crimeArea, String crimeDate, String crimeType, LatLng location, String speciesName) {
        this.crimeName = crimeName;
        this.crimeArea = crimeArea;
        this.crimeDate = crimeDate;
        this.crimeType = crimeType;
        this.location = location;
        this.speciesName = speciesName;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public String getCrimeArea() {
        return crimeArea;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public String getCrimeDate() {
        return crimeDate;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getSpeciesName() {
        return speciesName;
    }
}
