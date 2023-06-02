package com.solvd.online.store.locations;

public class Addresses {
    private int addressId;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Addresses(int addressId, String address, String city, String state, String postalCode, String country) {
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setAddressId(int addressId) {
    }

    public void setAddress(String address) {
    }

    public void setCity(String city) {
    }

    public void setState(String state) {
    }
}
