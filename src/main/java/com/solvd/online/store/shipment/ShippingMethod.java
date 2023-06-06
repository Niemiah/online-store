package com.solvd.online.store.shipment;

public class ShippingMethod {
    private int shippingId;
    private String methodName;
    private double shippingCost;
    private String shippingTime;

    public ShippingMethod(int shippingId, String methodName, double shippingCost, String shippingTime) {
        this.shippingId = shippingId;
        this.methodName = methodName;
        this.shippingCost = shippingCost;
        this.shippingTime = shippingTime;
    }

    public ShippingMethod() {

    }

    public int getShippingId() {
        return shippingId;
    }

    public String getMethodName() {
        return methodName;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingId(int shippingId) {
    }

    public void setMethodName(String methodName) {
    }

    public void setShippingCost(double shippingCost) {
    }

    public void setShippingTime(String shippingTime) {
    }
}
