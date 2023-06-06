package com.solvd.online.store.shipment;

import java.math.BigDecimal;

public class ShippingMethod {
    private int shippingId;
    private String methodName;
    private BigDecimal shippingCost;
    private String shippingTime;

    public ShippingMethod(int shippingId, String methodName, double shippingCost, String shippingTime) {
        this.shippingId = shippingId;
        this.methodName = methodName;
        this.shippingCost = shippingCost;
        this.shippingTime = shippingTime;
    }

    public int getShippingId() {
        return shippingId;
    }

    public String getMethodName() {
        return methodName;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingId(int shippingId) {
    }

    public void setMethodName(String methodName) {
    }

    public void setShippingCost(BigDecimal shippingCost) {
    }

    public void setShippingTime(String shippingTime) {
    }
}
