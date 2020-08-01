package com.example.codehead.criminalintent;


public class DiaryItem {

    private String imageUrl;
    private String commonName;
    private String date;
    private String areaName;

    public DiaryItem(String imageUrl, String commonName, String date, String areaName) {
        this.imageUrl = imageUrl;
        this.commonName = commonName;
        this.date = date;
        this.areaName = areaName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getDate() {
        return date;
    }
}
