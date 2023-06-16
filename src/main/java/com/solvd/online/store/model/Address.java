package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;

public class Address {
    @JsonProperty
    private int addressId;
    @JsonProperty
    private String address;
    @JsonProperty
    private String city;
    @JsonProperty
    private String state;
    @JsonProperty
    private String postalCode;
    @JsonProperty
    private String country;

    public Address() {
    }

    public Address(int addressId, String address, String city, String state, String postalCode, String country) {
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters and setters

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
