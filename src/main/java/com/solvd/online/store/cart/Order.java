package com.solvd.online.store.cart;

public class Order {
    private int orderId;
    private int userId;

    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setOrderId(int orderId) {
    }

    public void setUserId(int userId) {
    }
}
