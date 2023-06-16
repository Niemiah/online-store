package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;

public class ShippingMethod {
    @JsonProperty
    private int shippingId;
    @JsonProperty
    private String methodName;
    @JsonProperty
    private double shippingCost;
    @JsonProperty
    private String shippingTime;

    public ShippingMethod() {
    }

    public ShippingMethod(int shippingId, String methodName, double shippingCost, String shippingTime) {
        this.shippingId = shippingId;
        this.methodName = methodName;
        this.shippingCost = shippingCost;
        this.shippingTime = shippingTime;
    }

    // Getters and setters

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }
}