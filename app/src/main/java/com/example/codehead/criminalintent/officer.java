package com.example.codehead.criminalintent;

public class officer {
    private String rank;
    private String email_id;
    private String name;
    private String phone;
    private String username;
    private String pwd;
    private String area_name;
    private String pincode;

    public officer(String rank, String email_id, String name, String phone, String username, String pwd, String area_name, String pincode) {
        this.rank = rank;
        this.email_id = email_id;
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.pwd = pwd;
        this.area_name = area_name;
        this.pincode = pincode;
    }

    public String getRank() {
        return rank;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getArea_name() {
        return area_name;
    }

    public String getPincode() {
        return pincode;
    }
}
