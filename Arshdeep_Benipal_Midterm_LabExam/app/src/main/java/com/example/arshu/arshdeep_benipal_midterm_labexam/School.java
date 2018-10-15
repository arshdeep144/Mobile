package com.example.arshu.arshdeep_benipal_midterm_labexam;

/**
 * Created by Arshu on 2017-11-01.
 */

public class School {
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    public School(String name,
                  String address,
                  String city,
                  String postalCode,
                  String phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String toString() {
        return name + " - " + address + ", " + city + ", " + postalCode;
    }
}
