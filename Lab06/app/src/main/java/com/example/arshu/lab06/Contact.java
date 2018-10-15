package com.example.arshu.lab06;

/**
 * Created by Arshu on 2017-11-06.
 */

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public Contact(int id, String firstName, String lastName, String phoneNumber){
        this.id = id;
        this.firstName =firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
