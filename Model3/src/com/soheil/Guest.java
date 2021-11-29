package com.soheil;

import java.io.Serializable;

public class Guest implements Serializable {
    // To avoid error when deserializing after changes made in class that implements Serializable interface
    static final long serialVersionUID = 42L;

    // Attributes
    private String fullName;
    private String address;
    private int phoneNumber;

    // Constructor
    public Guest() {}
    public Guest(String fullName, String address, int phoneNumber) {
        setFullName(fullName);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    // getters
    public String getFullName() {
        return fullName;
    }
    public String getAddress() {
        return address;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    // setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
    // toString
}